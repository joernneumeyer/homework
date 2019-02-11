using System;

namespace PPAR_assignment1_cardealer.Commands
{
    public class SearchVehicleInPriceRangeCommand : IMenuCommand
    {
        public void Execute(CarDealer dealer)
        {
            double low, high;
            
            while (true)
            {
                Console.Write("Specify lowest price: ");
                string input = Console.ReadLine();
                if (double.TryParse(input, out low)) break;
            }
            
            while (true)
            {
                Console.Write("Specify highest price: ");
                string input = Console.ReadLine();
                if (double.TryParse(input, out high)) break;
            }
            
            (new PrintVehiclesCommand())
                .Execute(dealer.SearchVehicleInPriceRange(low, high));
        }
    }
}