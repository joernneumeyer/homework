using System;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Net.Mime;
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
            
            typeof(User).GetField("idCounter", BindingFlags.Static | BindingFlags.NonPublic).SetValue(null, int.Parse(ConfigurationManager.AppSettings["IdCounter"]));

            var db = factory.GetConnection();

            string input;
            AdministrationCommand cmd = null;
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
                    case "1":
                        cmd = AdministrationCommands.CreateNewUser;
                        break;
                    
                    case "2":
                        cmd = AdministrationCommands.ShowUsers;
                        break;
                    
                    case "3":
                        cmd = AdministrationCommands.ShowUserById;
                        break;
                    
                    default:
                        cmd = null;
                        break;
                }

                cmd?.Invoke(db);
            } while (input != "4");
        }
    }
}