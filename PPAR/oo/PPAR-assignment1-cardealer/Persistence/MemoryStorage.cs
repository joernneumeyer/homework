using System.Collections.Generic;
using System.Linq;
using PPAR_assignment1_cardealer.Vehicles;

namespace PPAR_assignment1_cardealer.Persistence
{
    public class MemoryStorage : IVehicleStorage
    {
        List<Vehicle> vehicles = new List<Vehicle>();
        
        public T Get<T>(int id)where T : Vehicle
        {
            return (T)this.vehicles.First(x => x.Id == id);
        }

        public void Insert<T>(T vehicle)where T : Vehicle
        {
            this.vehicles.Add(vehicle);
        }

        public void Update<T>(int id, T vehicle)where T : Vehicle
        {
            if (!this.vehicles.Any(x => x.Id == id)) return;
            this.vehicles.Remove(this.vehicles.First(x => x.Id == id));
            this.vehicles.Add(vehicle);
        }

        public void Delete(int id)
        {
            if (!this.vehicles.Any(x => x.Id == id)) return;
            this.vehicles.Remove(this.vehicles.First(x => x.Id == id));
        }

        public int GetVehicleCount()
        {
            return this.vehicles.Count;
        }

        public IEnumerable<Vehicle> GetAllAvailableVehicles()
        {
            return this.vehicles;
        }

        public double GetTotalValueOfAllVehicles()
        {
            return this.vehicles.Select(x => x.Price).Sum();
        }

        public IEnumerable<Vehicle> SearchVehicleByLicensePlate(string licensePlate)
        {
            return this.vehicles.Where(x => x.LicensePlate.Contains(licensePlate));
        }

        public IEnumerable<Vehicle> SearchVehiclesInPriceRange(double lowest, double highest)
        {
            return this.vehicles.Where(x => x.Price <= highest && x.Price >= lowest);
        }

        public void IncreaseAllPricesByPercentage(double percentage)
        {
            foreach (var vehicle in this.vehicles)
            {
                vehicle.Price = vehicle.Price * (1 + percentage / 100);
            }
        }
    }
}