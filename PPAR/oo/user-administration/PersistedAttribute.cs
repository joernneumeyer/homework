using System;

namespace user_administration
{
    public class PersistedAttribute : Attribute
    {
        public string PersistedName { get; }
        
        public PersistedAttribute(string persistedName = null)
        {
            this.PersistedName = persistedName;
        }
    }
}