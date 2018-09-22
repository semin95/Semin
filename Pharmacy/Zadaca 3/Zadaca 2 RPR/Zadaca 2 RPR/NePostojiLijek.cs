using System;


namespace Zadaca_2_RPR
{
    public class NePostojiLijek : Exception
    {
        public NePostojiLijek()
        {

        }
        public NePostojiLijek(string message) : base(message)
        {

        }
        public NePostojiLijek(string message , Exception inner) : base(message , inner)
        {

        }
    }
}
