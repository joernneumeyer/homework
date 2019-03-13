using System.Collections.Generic;
using PPAR_assignment1_cardealer.Vehicles;

namespace PPAR_assignment1_cardealer.Persistence
{
    public interface IVehicleStorage
    {
        T Get<T>(int id) where T : Vehicle;
        void Insert<T>(T vehicle) where T : Vehicle;
        void Update<T>(int id, T vehicle) where T : Vehicle;
        void Delete(int id);
        int GetVehicleCount();
        IEnumerable<Vehicle> GetAllAvailableVehicles();
        double GetTotalValueOfAllVehicles();
        IEnumerable<Vehicle> SearchVehicleByLicensePlate(string licensePlate);
        IEnumerable<Vehicle> SearchVehiclesInPriceRange(double lowest, double highest);
        void IncreaseAllPricesByPercentage(double percentage);
    }
}