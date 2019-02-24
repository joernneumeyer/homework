using System.Configuration;

namespace user_administration.Persistence
{
    public class XmlPersistenceFactory : IAbstractDbFactory
    {
        private static IAbstractDbFactory _instance = null;

        public static IAbstractDbFactory Instance
        {
            get
            {
                if (_instance == null)
                {
                    _instance = new XmlPersistenceFactory();
                }

                return _instance;
            }
        }

        private IPersistenceService connection = null;

        public IPersistenceService GetConnection()
        {
            if (connection == null)
            {
                connection = new XmlPersistenceService(ConfigurationManager.AppSettings["PersistenceDirectory"]);
            }

            return connection;
        }
    }
}