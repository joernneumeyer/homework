using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using PPAR_assignment1_cardealer.Vehicles;

namespace PPAR_assignment1_cardealer.Commands
{
    public class CreateVehicleCommand : IMenuCommand
    {
        private static object parseToPrimitiveType(string value, Type t)
        {
            if (!t.IsPrimitive)
            {
                throw new InvalidCastException("Cannot parse a type which is not a primitive type!");
            }

            if (t.Equals(typeof(int)))
            {
                return int.Parse(value);
            }
            else if (t.Equals(typeof(long)))
            {
                return long.Parse(value);
            }
            else if (t.Equals(typeof(short)))
            {
                return short.Parse(value);
            }
            else if (t.Equals(typeof(char)))
            {
                return char.Parse(value);
            }
            else if (t.Equals(typeof(float)))
            {
                return float.Parse(value);
            }
            else if (t.Equals(typeof(double)))
            {
                return double.Parse(value);
            }
            else
            {
                return null;
            }
        }

        public void Execute(CarDealer dealer)
        {
            var vehicleTypes = Assembly.GetExecutingAssembly().GetTypes().Where(t => t.BaseType == typeof(Vehicle))
                .ToArray();

            Vehicle v;
            IEnumerable<PropertyInfo> vehicleProperties;

            while (true)
            {
                Console.Write("Select vehicle kind(");
                for (int i = 0; i < vehicleTypes.Length; ++i)
                {
                    Console.Write($"{i.ToString()} {vehicleTypes[i].Name}");
                    if (i < vehicleTypes.Length - 1)
                    {
                        Console.Write(", ");
                    }
                }

                Console.Write("): ");
                string input = Console.ReadLine();
                int index;

                if (!int.TryParse(input, out index)) continue;
                if (index < 0 || index >= vehicleTypes.Length) continue;

                v = (Vehicle) Activator.CreateInstance(vehicleTypes[index]);
                vehicleProperties = vehicleTypes[index].GetProperties()
                    .Where(x => Attribute.IsDefined(x, typeof(VehicleProperty)));

                break;
            }

            foreach (var vehicleProperty in vehicleProperties)
            {
                while (true)
                {
                    Console.Write($"Please enter {vehicleProperty.Name}: ");
                    string input = Console.ReadLine();
                    if (vehicleProperty.PropertyType.Equals(typeof(string)))
                    {
                        vehicleProperty.SetValue(v, input);
                        break;
                    }
                    else
                    {
                        try
                        {
                            vehicleProperty.SetValue(v, parseToPrimitiveType(input, vehicleProperty.PropertyType));
                            break;
                        }
                        catch (FormatException e)
                        {
                        }
                    }
                }
            }

            /*while (true)
            {
                Console.Write("Enter price: ");
                string input = Console.ReadLine();
                double price;
                
                if (!double.TryParse(input, out price)) continue;
                v.Price = price;
                break;
            }
                
            Console.Write("Enter license plate: ");
            v.LicensePlate = Console.ReadLine();
            Console.Write("Enter vendor: ");
            v.Vendor = Console.ReadLine();*/

            dealer.AddVehicle(v);
        }
    }
}