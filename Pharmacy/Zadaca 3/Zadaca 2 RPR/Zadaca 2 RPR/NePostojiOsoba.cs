using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadaca_2_RPR
{
    public class NePostojiOsoba : Exception
    {
        public NePostojiOsoba()
        {

        }
        public NePostojiOsoba(string message) : base(message)
        {

        }
        public NePostojiOsoba(string message , Exception inner) : base(message, inner)
        {

        }
    }
}
