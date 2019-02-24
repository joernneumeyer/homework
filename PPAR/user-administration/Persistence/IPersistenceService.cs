using System.Runtime.CompilerServices;

namespace user_administration.Persistence
{
    public interface IPersistenceService
    {
        void Create<T>(T record) where T : IPersistableEntity, new();
        T Read<T>(int id) where T : IPersistableEntity, new();
        T Update<T>(int id, T record) where T : IPersistableEntity, new();
        void Delete<T>(int id) where T : IPersistableEntity, new();
    }
}