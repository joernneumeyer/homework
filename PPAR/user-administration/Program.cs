using System;
using System.Configuration;
using System.Linq;
using System.Reflection;
using user_administration.Persistence;
using user_administration.Users;

namespace user_administration
{
    class Program
    {
        static void Main(string[] args)
        {
            string dbFactoryClassName = ConfigurationManager.AppSettings["DBFactory"];
            var factory = (IAbstractDbFactory)Assembly
                .GetExecutingAssembly()
                .GetTypes()
                .First(x => x.Name == dbFactoryClassName)
                ?.GetConstructor(new Type[]{})
                ?.Invoke(new object[]{});
            
            Student s = new Student() { Class = "foo", Cohort = "bar", Nationality = "DE"};
            
            // factory.GetConnection().Create<Student>(s);
            factory.GetConnection().Read<Student>(0);

            /*string input;
            do
            {
                Console.WriteLine("1: Create User");
                Console.WriteLine("2: Show Overview");
                Console.WriteLine("3: Show Detail");
                Console.WriteLine("4: Exit");
                
                Console.Write("Please make a choice: ");
                input = Console.ReadLine();
                
                switch (input)
                {
                    
                }
                
                
            } while (input != "4");*/
        }
    }
}