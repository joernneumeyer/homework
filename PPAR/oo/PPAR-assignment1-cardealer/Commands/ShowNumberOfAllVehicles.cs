using System;

namespace PPAR_assignment1_cardealer.Commands
{
    public class ShowNumberOfAllVehicles : IMenuCommand
    {
        public void Execute(CarDealer dealer)
        {
            Console.WriteLine("Number of all available Vehicles: " + dealer.TotalVehiclesAvailable);
        }
    }
}