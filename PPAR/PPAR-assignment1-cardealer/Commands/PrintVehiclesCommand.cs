using System;
using System.Collections.Generic;
using System.Linq;
using PPAR_assignment1_cardealer.Vehicles;

namespace PPAR_assignment1_cardealer.Commands
{
    public class PrintVehiclesCommand : IMenuCommand
    {
        public void Execute(CarDealer dealer)
        {
            Execute(dealer.AllAvailableVehicles);
        }
        
        public void Execute(IEnumerable<Vehicle> vehicles)
        {
            if (!vehicles.Any())
            {
                Console.WriteLine("No vehicles available!");
                return;
            }
            
            int licensePlatePaddingLength = vehicles.Max(x => x.LicensePlate.Length);
            int vendorPaddingLength = vehicles.Max(x => x.Vendor.Length);
            int pricePaddingLength = vehicles.Max(x => x.Price.ToString().Length);
            
            
            if (licensePlatePaddingLength < 13) licensePlatePaddingLength = 13;
            if (vendorPaddingLength < 6) vendorPaddingLength = 6;
            if (pricePaddingLength < 5) pricePaddingLength = 5;
            Console.WriteLine($"| {"License Plate".PadRight(licensePlatePaddingLength)} | {"Vendor".PadRight(vendorPaddingLength)} | {"Price".PadRight(pricePaddingLength)} | Type");
            Console.WriteLine($"|-{"-".PadRight(licensePlatePaddingLength, '-')}-|-{"-".PadRight(vendorPaddingLength, '-')}-|-{"-".PadRight(pricePaddingLength, '-')}-|----");
            foreach (var vehicle in vehicles)
            {
                Console.WriteLine($"| {vehicle.LicensePlate.PadRight(licensePlatePaddingLength)} | {vehicle.Vendor.PadRight(vendorPaddingLength)} | {vehicle.Price.ToString().PadRight(pricePaddingLength)} | " + vehicle.GetType().Name);
            }
        }
    }
}