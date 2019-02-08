using System;
using System.Linq;
using System.Reflection;
using PPAR_assignment1_cardealer.Vehicles;

namespace PPAR_assignment1_cardealer.Commands
{
    public class CreateVehicleCommand : IMenuCommand
    {
        public void Execute(CarDealer dealer)
        {
            var vehicleTypes = Assembly.GetExecutingAssembly().GetTypes().Where(t => t.BaseType == typeof(Vehicle)).ToArray();

            Vehicle v;
            
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

                v = (Vehicle)Activator.CreateInstance(vehicleTypes[index]);

                break;
            }

            while (true)
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
            v.Vendor = Console.ReadLine();
            
            dealer.AddVehicle(v);
        }
    }
}