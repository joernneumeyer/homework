using System.Collections.Generic;
using System.Linq;
using PPAR_assignment1_cardealer.Vehicles;

namespace PPAR_assignment1_cardealer
{
    public class CarDealer
    {
        List<Vehicle> vehicles = new List<Vehicle>();

        public IEnumerable<Vehicle> AllAvailableVehicles => this.vehicles;

        public int TotalVehiclesAvailable => this.vehicles.Count;

        public double TotalValueOfAllVehicles => this.vehicles.Select(x => x.Price).Sum();

        public double? PriceOfVehicle(int id)
        {
            Vehicle v = this.vehicles.Find(x => x.Id == id);

            return v?.Price;
        }

        public void IncreaseAllPricesByPercentage(double percentage)
        {
            this.vehicles.ForEach(x => x.Price = x.Price * (1 + percentage / 100));
        }

        public IEnumerable<Vehicle> SearchVehicleByLicensePlate(string licensePlate)
        {
            return this.vehicles.Where(x => x.LicensePlate.Contains(licensePlate));
        }

        public IEnumerable<Vehicle> SearchVehicleInPriceRange(double lowest, double highest)
        {
            return this.vehicles.Where(x => x.Price <= highest && x.Price >= lowest);
        }

        public void AddVehicle(Vehicle v)
        {
            this.vehicles.Add(v);
        }
    }
}