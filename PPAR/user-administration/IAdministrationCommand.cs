using user_administration.Persistence;

namespace user_administration
{
    public interface IAdministrationCommand
    {
        void Execute(IPersistenceService connection);
    }
}