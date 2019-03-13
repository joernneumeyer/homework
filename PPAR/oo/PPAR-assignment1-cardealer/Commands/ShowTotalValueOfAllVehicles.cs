using System;

namespace PPAR_assignment1_cardealer.Commands
{
    public class ShowTotalValueOfAllVehicles : IMenuCommand
    {
        public void Execute(CarDealer dealer)
        {
            Console.WriteLine("Total value of all vehicles available: " + dealer.TotalValueOfAllVehicles);
        }
    }
}