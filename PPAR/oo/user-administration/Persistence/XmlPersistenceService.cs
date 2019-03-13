using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.IO;
using System.Linq;
using System.Net;
using System.Reflection;
using System.Xml.Linq;

namespace user_administration.Persistence
{
    public class XmlPersistenceService : IPersistenceService
    {
        private readonly string persistenceDirectory;

        public XmlPersistenceService(string persistenceDirectory)
        {
            this.persistenceDirectory = persistenceDirectory;
        }

        private string getPersistencePath<T>() where T : IPersistableEntity
        {
            return this.persistenceDirectory + "/" + typeof(T).Name + ".xml";
        }

        private XElement readPersistenceFile<T>() where T : IPersistableEntity
        {
            string filePath = this.getPersistencePath<T>();
            if (!File.Exists(filePath))
            {
                File.WriteAllText(filePath, "<data></data>");
            }

            XElement root = XElement.Load(filePath);

            return root;
        }

        private T deserialize<T>(XElement xml) where T : IPersistableEntity, new()
        {
            T instance = new T();
            var props = typeof(T).GetProperties().Where(x => Attribute.IsDefined(x, typeof(PersistedAttribute)));

            foreach (var n in xml.Nodes())
            {
                var node = (XElement) n;
                var prop = props.First(x =>
                {
                    string name = x.Name;
                    var attr = x.GetCustomAttribute<PersistedAttribute>();
                    if (attr.PersistedName != null)
                    {
                        name = attr.PersistedName;
                    }
                    return name == node.Name;
                });
                if (prop == null) continue;
                prop.SetValue(instance, Transformers.GetFittingTransformerFromString(prop.PropertyType)(node.Value));
                /*var value = prop
                    .PropertyType
                    .GetConstructor(new[] {typeof(string)})
                    ?.Invoke(new object[] {node.Value});*/
                //if (value == null) continue;
                //prop.SetValue(instance, value);
            }

            return instance;
        }

        private XElement serialize<T>(T entity) where T : IPersistableEntity
        {
            var props = typeof(T).GetProperties().Where(x => Attribute.IsDefined(x, typeof(PersistedAttribute)));

            XElement child = new XElement(typeof(T).Name);
            foreach (var prop in props)
            {
                string propName = prop.Name;
                {
                    var attr = prop.GetCustomAttribute<PersistedAttribute>();
                    if (attr.PersistedName != null)
                    {
                        propName = attr.PersistedName;
                    }
                }
                
                XElement propChild = new XElement(propName);
                propChild.SetValue(Transformers.GetFittingTransformerToString(prop.PropertyType)(prop.GetValue(entity)));
                child.Add(propChild);
            }

            return child;
        }

        public void Create<T>(T record) where T : IPersistableEntity, new()
        {
            if (typeof(T).IsAbstract)
            {
                throw new ArgumentException("Cannot persist abstract types!");
            }

            var root = readPersistenceFile<T>();
            root.Add(serialize(record));

            root.Save(this.getPersistencePath<T>());
        }

        public T Read<T>(int id) where T : IPersistableEntity, new()
        {
            T instance = new T();
            XElement root = this.readPersistenceFile<T>();
            foreach (var node in root.Nodes())
            {
                T entity = this.deserialize<T>((XElement) node);
                if (entity.Id == id) return entity;
            }

            return instance;
        }

        public T Update<T>(int id, T record) where T : IPersistableEntity, new()
        {
            throw new System.NotImplementedException();
        }

        public void Delete<T>(int id) where T : IPersistableEntity, new()
        {
            throw new System.NotImplementedException();
        }

        public IEnumerable<T> ReadAll<T>() where T : IPersistableEntity, new()
        {
            XElement root = readPersistenceFile<T>();
            List<T> tuples = new List<T>();
            foreach (var node in root.Nodes())
            {
                tuples.Add(deserialize<T>((XElement)node));
            }

            return tuples;
        }
    }
}