using Newtonsoft.Json;

namespace PPAR_assignment1_cardealer.Vehicles
{
    public abstract class Vehicle
    {
        private static int nextVehicleId = 0;
        [JsonProperty] public int Id { get; }
        [JsonProperty] [VehicleProperty] public double Price { get; set; } = 0;
        [JsonProperty] [VehicleProperty] public string LicensePlate { get; set; } = "";
        [JsonProperty] [VehicleProperty] public string Vendor { get; set; } = "";

        public Vehicle()
        {
            this.Id = ++nextVehicleId;
        }
    }
}