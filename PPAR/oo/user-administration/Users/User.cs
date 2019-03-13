using System;
using System.Text.RegularExpressions;
using user_administration.Persistence;

namespace user_administration.Users
{
    public abstract class User : IPersistableEntity
    {
        private static int idCounter = 0;
        
        private static Regex emailRegex = new Regex(@"^[a-zA-Z-_0-9]+@[a-zA-Z-_0-9]+\.[a-zA-Z]+$");

        public User()
        {
            if (this.Id == 0)
            {
                this.Id = ++idCounter;
            }
        }
        
        [PersistedAttribute("last_name")]
        public string LastName { get; set; }
        [PersistedAttribute("first_name")]
        public string FirstName { get; set; }

        private string email;
        [PersistedAttribute("email")] 
        public string Email
        {
            get { return this.email; }
            set
            {
                if (value == "")
                {
                    this.email = "";
                    return;
                }
                if (!emailRegex.IsMatch(value))
                {
                    throw new ArgumentException($"E-Mail has to match pattern {emailRegex.ToString()}! Given E-Mail was {value}");
                }
                this.email = value;
            }
        }
        [PersistedAttribute("nationality")]
        public string Nationality { get; set; }
        [PersistedAttribute("id")]
        public int Id { get; set; }
    }
}