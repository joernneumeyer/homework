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
            var props = typeof(T).GetProperties();

            foreach (var n in xml.Nodes())
            {
                var node = (XElement) n;
                var prop = props.First(x => x.Name == node.Name);
                if (prop == null) continue;
                if (prop.PropertyType.Name == nameof(String))
                {
                    prop.SetValue(instance, node.Value);
                    continue;
                }
                var value = prop
                    .PropertyType
                    .GetConstructor(new[] {typeof(string)})
                    ?.Invoke(new object[] {node.Value});
                if (value == null) continue;
                prop.SetValue(instance, value);
            }

            return instance;
        }

        public void Create<T>(T record) where T : IPersistableEntity, new()
        {
            if (typeof(T).IsAbstract)
            {
                throw new ArgumentException("Cannot persist abstract types!");
            }

            var props = typeof(T).GetProperties();

            XElement root = this.readPersistenceFile<T>();
            XElement child = new XElement(typeof(T).Name);
            foreach (var prop in props)
            {
                XElement propChild = new XElement(prop.Name);
                propChild.SetValue(prop.GetValue(record)?.ToString() ?? "");
                child.Add(propChild);
            }

            root.Add(child);

            root.Save(this.getPersistencePath<T>());
        }

        public T Read<T>(int id) where T : IPersistableEntity, new()
        {
            T instance = new T();
            XElement root = this.readPersistenceFile<T>();
            foreach (var node in root.Nodes())
            {
                this.deserialize<T>((XElement) node);
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
    }
}