using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Runtime.CompilerServices;
using PPAR_assignment1_cardealer.Commands;
using PPAR_assignment1_cardealer.Persistence;
using PPAR_assignment1_cardealer.Vehicles;

namespace PPAR_assignment1_cardealer
{
    class Program
    {
        static void Main(string[] args)
        {
            IVehicleStorage storage = JsonStorage.LoadFromFile("/home/joernneumeyer/vehicles");
            CarDealer dealer = new CarDealer(storage);
            IMenuCommand cmd;
            
            Vehicle v = new Car();
            v.Price = 200;
            v.Vendor = "Skoda";
            v.LicensePlate = "ABC";
            dealer.AddVehicle(v);
            
            v = new Truck();
            v.Price = 300;
            v.Vendor = "MAN";
            v.LicensePlate = "YTG";
            dealer.AddVehicle(v);

            bool repeat = true;
            
            do
            {
                Console.WriteLine("Please select an option:");
                Console.WriteLine("q: Quit");
                Console.WriteLine("1: Add a new vehicle");
                Console.WriteLine("2: List all available vehicles");
                Console.WriteLine("3: Show number of available vehicles");
                Console.WriteLine("4: Show total value of all vehicles");
                // Console.WriteLine("5: Show price of vehicle by id");
                Console.WriteLine("6: Increase all prices by percentage");
                Console.WriteLine("7: Search for vehicle in price range");

                string choice = Console.ReadLine();

                switch (choice)
                {
                    case "1":
                        cmd = new CreateVehicleCommand();
                        break;
                    case "2":
                        cmd = new PrintVehiclesCommand();
                        break;
                    case "3":
                        cmd = new ShowNumberOfAllVehicles();
                        break;
                    case "4":
                        cmd = new ShowTotalValueOfAllVehicles();
                        break;
                    case "6":
                        cmd = new IncreasePricesCommand();
                        break;
                    case "7":
                        cmd = new SearchVehicleInPriceRangeCommand();
                        break;
                    case "q":
                        cmd = null;
                        repeat = false;
                        break;
                    default:
                        cmd = null;
                        break;
                }

                if (cmd != null)
                {
                    cmd.Execute(dealer);
                    Console.Write("\r\n\r\nPress enter...");
                    Console.ReadLine();
                    Console.Clear();
                }
            } while (repeat);
        }
    }
}