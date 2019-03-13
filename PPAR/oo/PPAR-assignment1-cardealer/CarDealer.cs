using System.Collections.Generic;
using System.Linq;
using PPAR_assignment1_cardealer.Persistence;
using PPAR_assignment1_cardealer.Vehicles;

namespace PPAR_assignment1_cardealer
{
    public class CarDealer
    {
        private IVehicleStorage storage;

        public CarDealer(IVehicleStorage storage)
        {
            this.storage = storage;
        }

        public IEnumerable<Vehicle> AllAvailableVehicles => this.storage.GetAllAvailableVehicles();

        public int TotalVehiclesAvailable => this.storage.GetVehicleCount();

        public double TotalValueOfAllVehicles => this.storage.GetTotalValueOfAllVehicles();

        public double? PriceOfVehicle(int id)
        {
            Vehicle v = this.storage.Get<Vehicle>(id);

            return v?.Price;
        }

        public void IncreaseAllPricesByPercentage(double percentage)
        {
            this.storage.IncreaseAllPricesByPercentage(percentage);
        }

        public IEnumerable<Vehicle> SearchVehicleByLicensePlate(string licensePlate)
        {
            return this.storage.SearchVehicleByLicensePlate(licensePlate);
        }

        public IEnumerable<Vehicle> SearchVehicleInPriceRange(double lowest, double highest)
        {
            return this.storage.SearchVehiclesInPriceRange(lowest, highest);
        }

        public void AddVehicle(Vehicle v)
        {
            this.storage.Insert(v);
        }
    }
}