using System;

namespace PPAR_assignment1_cardealer.Commands
{
    public class IncreasePricesCommand : IMenuCommand
    {
        public void Execute(CarDealer dealer)
        {
            double percentage;

            while (true)
            {
                Console.Write("Specify percentage: ");
                string input = Console.ReadLine();
                if (double.TryParse(input, out percentage)) break;
            }
            
            dealer.IncreaseAllPricesByPercentage(percentage);
        }
    }
}