using System;

namespace user_administration.Users
{
    public class Lecturer : User
    {
        [PersistedAttribute("phone")]
        public string PhoneNumber { get; set; }
        [PersistedAttribute("abbr")]
        public string Abbreviation { get; set; }
        [PersistedAttribute("started")]
        public DateTime DateStarted { get; set; }
    }
}