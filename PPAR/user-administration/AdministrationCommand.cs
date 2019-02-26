using user_administration.Persistence;

namespace user_administration
{
    public delegate void AdministrationCommand(IPersistenceService db);
}