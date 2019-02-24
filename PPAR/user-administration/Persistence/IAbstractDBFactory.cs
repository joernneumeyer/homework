using System.Data;

namespace user_administration.Persistence
{
    public interface IAbstractDbFactory
    {
        IPersistenceService GetConnection();
    }
}