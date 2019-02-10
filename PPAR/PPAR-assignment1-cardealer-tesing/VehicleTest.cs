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
            Vehicle car = new Car();
            Vehicle car2 = new Car();
            Assert.NotEqual(car.Id, car2.Id);
        }
    }
}