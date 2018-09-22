using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Zadaca_2_RPR
{
    public partial class Pravila : Form
    {
        public Pravila()
        {
            InitializeComponent();
        }

        private void unesi_b_Click(object sender, EventArgs e)
        {
            try
            {
                if (!GlobalneVarijable.lijekovi.Contains(GlobalneVarijable.lijekovi.Find(x => x.naziv == nazivPrvogLijeka_t.Text)))
                    throw (new NePostojiLijek("Ne postoji prvi lijek koji ste unijeli!"));
                if (!GlobalneVarijable.lijekovi.Contains(GlobalneVarijable.lijekovi.Find(x => x.naziv == nazivDrugogLijeka_t.Text)))
                    throw (new NePostojiLijek("Ne postoji drugi lijek koji ste unijeli!"));
            }
            catch(NePostojiLijek izuzetak)
            {
                MessageBox.Show(izuzetak.Message , "Upozorenje!");
                return;
            }
            
            GlobalneVarijable.regulacijaPravila.Add(new RegulacijaPravila(nazivPrvogLijeka_t.Text, nazivDrugogLijeka_t.Text));
            MessageBox.Show("Uspjesno je uneseno!", "Potvrda");
            this.Close();
        }


    }
}
