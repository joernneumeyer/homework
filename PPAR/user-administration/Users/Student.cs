namespace user_administration.Users
{
    public class Student : User
    {
        [PersistedAttribute("study_program")]
        public string StudyProgram { get; set; }
        [PersistedAttribute("cohort")]
        public string Cohort { get; set; }
        [PersistedAttribute("class")]
        public string Class { get; set; }
    }
}