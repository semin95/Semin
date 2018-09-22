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
    public partial class UnosOsobe : Form
    {
        bool crossValidacija()
        {
            int godina = datumRodjenja_d.Value.Year;

            
            if(izbor_c.Text == "Dijete" && DateTime.Now.Year - godina > 18)
            {
                error.SetError(this.datumRodjenja_d, "Dijete ne moze biti starije od 18 godina!");
                statusna_linija.Text = "Dijete ne moze biti starije od 18 godina!";
                datumRodjenja_d.Focus();
                return false;
            }
            else if (izbor_c.Text == "Obicni" && DateTime.Now.Year - godina < 18)
            {
                error.SetError(this.datumRodjenja_d, "Obicni kupac ne moze biti mladi od 18 godina!");
                statusna_linija.Text = "Obicni kupac ne moze biti mladi od 18 godina!";
                datumRodjenja_d.Focus();
                return false;
            }
            else if(izbor_c.Text == "Penzioner" && DateTime.Now.Year - godina < 65)
            {
                error.SetError(this.datumRodjenja_d, "Penzioner mora biti imati vise od 65 godina!");
                statusna_linija.Text = "Penzioner mora biti imati vise od 65 godina!";
                datumRodjenja_d.Focus();
                return false;
            }
                
            return true;
        }

        bool Validacija()
        {

            if (ime_t.Text == "")
            {
                statusna_linija.Text = "Nije uneseno ime osobe!";
                error.SetError(this.ime_t, "Nije uneseno ime osobe!");
                ime_t.Focus();
                return false;
            }
            else
            {
                statusna_linija.Text = "";
                error.SetError(this.ime_t, string.Empty);
            }

            if (prezime_t.Text == "")
            {
                statusna_linija.Text = "Nije uneseno prezime osobe!";
                error.SetError(this.prezime_t, "Nije uneseno prezime osobe!");
                prezime_t.Focus();
                return false;
            }
            else
            {
                statusna_linija.Text = "";
                error.SetError(this.prezime_t, string.Empty);
            }

            Int32 ID = Convert.ToInt32(id_n.Value);
            if (GlobalneVarijable.kupci.Contains(GlobalneVarijable.kupci.Find(x => x.ID == ID)) ||
              GlobalneVarijable.prodavaci.Contains(GlobalneVarijable.prodavaci.Find(x => x.ID == ID)))
            {
                error.SetError(this.id_n, "Vec postoji osoba sa unesenim ID-om");
                statusna_linija.Text = "Vec postoji osoba sa unesenim ID-om";
                id_n.Focus();
                return false;
            }
            else
            {
                statusna_linija.Text = "";
                error.SetError(this.id_n, string.Empty);
            }

            if(datumRodjenja_d.Value > DateTime.Now)
            {
                error.SetError(this.datumRodjenja_d, "Rodjeni u buducnosti?");
                statusna_linija.Text = "Rodjeni u buducnosti?";
                datumRodjenja_d.Focus();
                return false;
            }
            else
            {
                statusna_linija.Text = "";
                error.SetError(this.datumRodjenja_d, string.Empty);
            }

            return true;
        }

        public UnosOsobe()
        {
            InitializeComponent();
        }

        public UnosOsobe(Prodavac p)
        {
            InitializeComponent();
            ime_t.Text = p.ime;
            prezime_t.Text = p.prezime;
            id_n.Value = p.ID;
            datumRodjenja_d.Value = p.datumRodjenja;
            izbor_c.Text = "Prodavac";
        }
        public UnosOsobe(Kupac osoba)
        {
            InitializeComponent();

            ime_t.Text = osoba.ime;
            prezime_t.Text = osoba.prezime;
            id_n.Value = osoba.ID;
            datumRodjenja_d.Value = osoba.datumRodjenja;

            if (osoba is Dijete)
            {
                izbor_c.Text = "Dijete";
                Dijete dijete = osoba as Dijete;
                foreach(string s in dijete.listaAlergija)
                {
                    listaAlergija_l.Items.Add(s);
                }
            }
            else if (osoba is Obicni)
            {
                izbor_c.Text = "Obicni";
                Obicni obicni = osoba as Obicni;
                foreach (string s in obicni.listaAlergija)
                {
                    listaAlergija_l.Items.Add(s);
                }
            }
            else if (osoba is Penzioner)
            {
                izbor_c.Text = "Penzioner";
                Penzioner penzioner = osoba as Penzioner;
                foreach (string s in penzioner.listaAlergija)
                {
                    listaAlergija_l.Items.Add(s);
                }
            }
            else
            {
                izbor_c.Text = "";
            }
        }

        private void listaAlergija_b_Click(object sender, EventArgs e)
        {
            listaAlergija_l.Items.Add(listaAlergija_t.Text);
            listaAlergija_t.Text = "";
        }

        private void unesiKupca_b_Click(object sender, EventArgs e)
        {
            if (!Validacija())
                return;

            if (!crossValidacija())
                return;

            string ime = ime_t.Text;
            string prezime = prezime_t.Text;
            Int32 ID = Convert.ToInt32(id_n.Value);

            DateTime datumRodjenja = datumRodjenja_d.Value;
            List<string> listaAlergija = new List<string>();
            foreach (string s in listaAlergija_l.Items)
            {
                listaAlergija.Add(s);
            }
            if (izbor_c.SelectedItem.Equals("Dijete"))
                GlobalneVarijable.kupci.Add(new Dijete(ID, ime, prezime, datumRodjenja, listaAlergija));
            else if (izbor_c.SelectedItem.Equals("Obicni"))
                GlobalneVarijable.kupci.Add(new Obicni(ID, ime, prezime, datumRodjenja, listaAlergija));
            else
                GlobalneVarijable.kupci.Add(new Penzioner(ID, ime, prezime, datumRodjenja, listaAlergija));
            this.Close();
            MessageBox.Show("Osoba je uspjesno unesena!", "Potvrda");
        }

        private void unesiProdavaca_b_Click(object sender, EventArgs e)
        {
            int godina = datumRodjenja_d.Value.Year;
            if (izbor_c.Text == "Prodavac" && DateTime.Now.Year - godina < 18)
            {
                error.SetError(this.datumRodjenja_d, "Prodavac mora imati vise od 18 godina!");
                statusna_linija.Text = "Prodavac mora imati vise od 18 godina!";
                datumRodjenja_d.Focus();
                return;
            }

            string ime = ime_t.Text;
            string prezime = prezime_t.Text;
            Int32 ID = Convert.ToInt32(id_n.Value);
            DateTime datumRodjenja = datumRodjenja_d.Value;
            GlobalneVarijable.prodavaci.Add(new Prodavac(ID, ime, prezime, datumRodjenja));
            this.Close();
            MessageBox.Show("Osoba je uspjesno unesena!" , "Potvrda");
        }

        private void izbor_c_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (izbor_c.SelectedItem.Equals("Prodavac"))
                postaviZaProdavaca();
            else
                postaviZaKupca();
        }

        void postaviZaProdavaca()
        {
            listaAlergija_g.Visible = false;
            unesiKupca_b.Visible = false;
            unesiProdavaca_b.Visible = true;
        }
        void postaviZaKupca()
        {
            listaAlergija_g.Visible = true;
            unesiKupca_b.Visible = true;
            unesiProdavaca_b.Visible = false;
        }

    }
}
