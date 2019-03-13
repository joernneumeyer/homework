using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using PPAR_assignment1_cardealer.Vehicles;
using Newtonsoft.Json;

namespace PPAR_assignment1_cardealer.Persistence
{
    public class JsonStorage : IVehicleStorage
    {
        private string storageFilePath;
        private Dictionary<string, List<Vehicle>> vehicles = new Dictionary<string, List<Vehicle>>();
        
        private JsonStorage()
        {
            
        }

        public static JsonStorage LoadFromFile(string folderPath)
        {
            JsonStorage s = new JsonStorage();
            if (!Directory.Exists(folderPath))
            {
                throw new ArgumentException("The given file does not exist!");
            }
            var vehicleTypes = Assembly.GetExecutingAssembly().GetTypes().Where(x => x.IsSubclassOf(typeof(Vehicle)));

            foreach (var vehicleType in vehicleTypes)
            {
                string typeStorageFilePath = $"{folderPath}/{vehicleType.Name}.json";
                if (!File.Exists(typeStorageFilePath))
                {
                    s.vehicles[vehicleType.Name] = new List<Vehicle>();
                    continue;
                }
                string fileContent = File.ReadAllText(typeStorageFilePath);
                var deserialize = typeof(JsonConvert).GetMethod("DeserializeObject").MakeGenericMethod(vehicleType.MakeArrayType());
                s.vehicles[vehicleType.Name] = ((Vehicle[])deserialize.Invoke(null, new []{fileContent})).ToList();
                
            }

            return s;
        }

        public T Get<T>(int id) where T : Vehicle
        {
            throw new System.NotImplementedException();
        }

        public void Insert<T>(T vehicle) where T : Vehicle
        {
            throw new System.NotImplementedException();
        }

        public void Update<T>(int id, T vehicle) where T : Vehicle
        {
            throw new System.NotImplementedException();
        }

        public void Delete(int id)
        {
            throw new System.NotImplementedException();
        }

        public int GetVehicleCount()
        {
            throw new System.NotImplementedException();
        }

        public IEnumerable<Vehicle> GetAllAvailableVehicles()
        {
            throw new System.NotImplementedException();
        }

        public double GetTotalValueOfAllVehicles()
        {
            throw new System.NotImplementedException();
        }

        public IEnumerable<Vehicle> SearchVehicleByLicensePlate(string licensePlate)
        {
            throw new System.NotImplementedException();
        }

        public IEnumerable<Vehicle> SearchVehiclesInPriceRange(double lowest, double highest)
        {
            throw new System.NotImplementedException();
        }

        public void IncreaseAllPricesByPercentage(double percentage)
        {
            throw new System.NotImplementedException();
        }
    }
}