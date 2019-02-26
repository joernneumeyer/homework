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

            if (factory == null)
            {
                Console.WriteLine("could not instantiate database factory from app.config!");
                return;
            }
            
            Student s = new Student() { FirstName = "foos", LastName= "bars", Nationality = "DE"};
            Lecturer l = new Lecturer()
            {
                Nationality = "NL",
                Email = "some@thing.nl",
                FirstName = "foo",
                LastName = "bar",
                Abbreviation = "fb",
                DateStarted = new DateTime(2015, 5, 1)
            };
            
            // factory.GetConnection().Create(s);
            // Lecturer l2 = factory.GetConnection().Read<Lecturer>(0);

            AdministrationCommand cmd;
            
            AdministrationCommands.ShowUserById(factory.GetConnection());

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