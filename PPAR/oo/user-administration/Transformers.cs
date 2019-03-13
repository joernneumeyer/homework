using System;
using System.Collections.Generic;

namespace user_administration
{
    public static class Transformers
    {
        private static Dictionary<Type, Func<string, object>> transformersFromString =
            new Dictionary<Type, Func<string, object>>()
            {
                {typeof(DateTime), x => DateTime.Parse(x)},
                {typeof(int), x => int.Parse(x)}
            };

        private static Dictionary<Type, Func<object, string>> transformersToString =
            new Dictionary<Type, Func<object, string>>()
            {
            };

        public static Func<string, object> GetFittingTransformerFromString(Type t)
        {
            try
            {
                return transformersFromString[t];
            }
            catch (KeyNotFoundException e)
            {
                return s => s;
            }
        }

        public static Func<object, string> GetFittingTransformerToString(Type t)
        {
            try
            {
                return transformersToString[t];
            }
            catch (KeyNotFoundException e)
            {
                return s => s?.ToString() ?? "";
            }
        }
    }
}