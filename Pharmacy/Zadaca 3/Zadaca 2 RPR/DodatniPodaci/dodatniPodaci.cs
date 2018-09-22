using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Zadaca_2_RPR;

namespace DodatniPodaci
{
    public partial class dodatniPodaci_u: UserControl
    {
        public List<PodaciOKorisniku> podaciOKorisniku = new List<PodaciOKorisniku>();
        public dodatniPodaci_u()
        {
            InitializeComponent();
        }

        private void UserControl1_Load(object sender, EventArgs e)
        {
            
        }

        private void unesi_b_Click(object sender, EventArgs e)
        {
            int id = 0;
            try 
            {
                id = Convert.ToInt32(id_t.Text);
                errorProvider1.SetError(this.id_t , string.Empty);
            }
            catch(FormatException)
            {
                errorProvider1.SetError(this.id_t , "Neodgovarajuci format!");
                return;
            }

            try
            {
                if (!(GlobalneVarijable.kupci.Contains(GlobalneVarijable.kupci.Find(x => x.ID == id)) ||
                GlobalneVarijable.prodavaci.Contains(GlobalneVarijable.prodavaci.Find(x => x.ID == id))))
                    throw (new NePostojiOsoba("Ne postoji osoba sa ID-om koji ste unijeli!"));

                errorProvider1.SetError(this.id_t, string.Empty);
                podaciOKorisniku.Add(new PodaciOKorisniku(id, grad_t.Text, adresa_t.Text, brojTelefona_t.Text, emailAdresa_t.Text));
                id_t.Text = "";
                grad_t.Text = "";
                adresa_t.Text = "";
                brojTelefona_t.Text = "";
                emailAdresa_t.Text = "";

                MessageBox.Show("Podaci su uspjesno uneseni!", "Potvrda");
                return;
            }
            catch(NePostojiOsoba izuzetak)
            {
                errorProvider1.SetError(this.id_t, izuzetak.Message);
                return;
            }
        }

    }
}
