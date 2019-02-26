using System;
using System.Collections.Generic;
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
            var users = getAllUsers(db);

            foreach (var user in users)
            {
                Console.WriteLine($"{user.Id} - {user.LastName}, {user.FirstName}");
            }
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
        }
    }
}