namespace user_administration.Users
{
    public class Student : User
    {
        public string StudyProgram { get; set; }
        public string Cohort { get; set; }
        public string Class { get; set; }
    }
}