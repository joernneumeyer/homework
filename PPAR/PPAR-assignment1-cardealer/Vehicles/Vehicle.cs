namespace PPAR_assignment1_cardealer.Vehicles
{
    public abstract class Vehicle
    {
        private static int nextVehicleId = 0;
        public int Id { get; }
        public double Price { get; set; }
        public string LicensePlate { get; set; }
        public string Vendor { get; set; }

        public Vehicle()
        {
            this.Id = ++nextVehicleId;
        }
    }
}