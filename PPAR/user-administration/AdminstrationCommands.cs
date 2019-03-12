using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Reflection;
using user_administration.Persistence;
using user_administration.Users;

namespace user_administration
{
    public static class AdministrationCommands
    {
        private static IEnumerable<Type> getAllUserTypes()
        {
            return Assembly.GetExecutingAssembly().GetTypes().Where(x => x.IsSubclassOf(typeof(User)));
        }
        
        private static IEnumerable<User> getAllUsers(IPersistenceService db)
        {
            List<User> users = new List<User>();
            var userTypes = getAllUserTypes();
            foreach (var userType in userTypes)
            {
                var someUsers = (IEnumerable<User>)typeof(IPersistenceService).GetMethod("ReadAll").MakeGenericMethod(userType).Invoke(db, new object[]{ });
                users.AddRange(someUsers);
            }

            return users;
        }
        
        public static void ShowUsers(IPersistenceService db)
        {
            Console.WriteLine("List of users\r\n");
            var users = getAllUsers(db);

            foreach (var user in users)
            {
                Console.WriteLine($"{user.Id} - {user.LastName}, {user.FirstName}");
            }

            Console.WriteLine("--------------------");
        }

        public static void ShowUserById(IPersistenceService db)
        {
            int id;
            while (true)
            {
                Console.Write("Please enter an Id: ");
                if (int.TryParse(Console.ReadLine(), out id)) break;
            }

            var user = getAllUsers(db).First(x => x.Id == id);
            if (user == null)
            {
                Console.WriteLine("There is no user with the specified id!");
            }
            else
            {
                var props = user.GetType().GetProperties()
                    .Where(x => Attribute.IsDefined(x, typeof(PersistedAttribute)));
                foreach (var prop in props)
                {
                    Console.WriteLine($"{prop.Name}: {prop.GetValue(user)}");
                }
            }
            Console.WriteLine("--------------------");
        }

        public static void CreateNewUser(IPersistenceService db)
        {
            object newUser = null;
            Type[] userTypes = Assembly.GetAssembly(typeof(User)).GetTypes().Where(x => x.IsSubclassOf(typeof(User))).ToArray();
            Type userTypeChoice = null;
            do
            {
                Console.Write("Please select a user type (");
                for (int i = 0; i < userTypes.Length; i++)
                {
                    Console.Write(i + " " + userTypes[i].Name);
                    if (i < userTypes.Length - 1)
                    {
                        Console.Write(", ");
                    }
                }
                Console.Write("): ");
                if (!int.TryParse(Console.ReadLine(), out var choiceIndex))
                {
                    continue;
                }

                if (choiceIndex >= 0 && choiceIndex < userTypes.Length)
                {
                    userTypeChoice = userTypes[choiceIndex];
                }
            } while (userTypeChoice == null);

            newUser = Activator.CreateInstance(userTypeChoice);

            var props = userTypeChoice.GetProperties().Where(x => Attribute.IsDefined(x, typeof(PersistedAttribute)));

            foreach (var prop in props)
            {
                while (true)
                {
                    if (prop.Name == "Id") break;
                    Console.Write($"Value for property '{prop.Name}': ");
                    string input = Console.ReadLine();
                    if (String.IsNullOrWhiteSpace(input)) continue;
                    try
                    {
                        object result = Transformers.GetFittingTransformerFromString(prop.PropertyType)(input);
                        prop.SetValue(newUser, result);
                        break;
                    }
                    catch (Exception e)
                    {
                        // ignored
                    }
                }
            }

            var genericCreate = db.GetType().GetMethod("Create").MakeGenericMethod(userTypeChoice);
            genericCreate.Invoke(db, new object[] {newUser});
            // ConfigurationManager.AppSettings.Set("IdCounter", ((User)newUser).Id.ToString());
            ConfigurationManager.AppSettings["IdCounter"] = ((User) newUser).Id.ToString();
            Console.WriteLine("User has been created");
            Console.WriteLine("--------------------");
        }
    }
}