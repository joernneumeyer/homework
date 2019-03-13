using Newtonsoft.Json;

namespace PPAR_assignment1_cardealer.Vehicles
{
    public class Bike : Vehicle
    {
        [JsonProperty] [VehicleProperty] public int NumberOfSeats { get; set; }
    }
}