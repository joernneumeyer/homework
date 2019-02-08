using System;
using PPAR_assignment1_cardealer.Vehicles;
using Xunit;

namespace PPAR_assignment1_cardealer_tesing
{
    public class VehicleTest
    {
        [Fact]
        public void TestVehicleIdIncreasesIfANewVehicleIsCreated()
        {
            Vehicle car = new Car(100, "BMW");
            Vehicle car2 = new Car(100, "BMW");
            Assert.NotEqual(car.Id, car2.Id);
        }
    }
}