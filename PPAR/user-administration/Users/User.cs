using user_administration.Persistence;

namespace user_administration.Users
{
    public abstract class User : IPersistableEntity
    {
        public string LastName { get; set; }
        public string FirstName { get; set; }
        public string Email { get; set; }
        public string Nationality { get; set; }
        public int Id { get; set; } = 0;
    }
}