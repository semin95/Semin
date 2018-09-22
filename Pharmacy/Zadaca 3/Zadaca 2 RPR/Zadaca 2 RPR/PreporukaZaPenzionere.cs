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
    public partial class PreporukaZaPenzionere : Form
    {
        public PreporukaZaPenzionere()
        {
            InitializeComponent();
        }

        private void unesi_b_Click(object sender, EventArgs e)
        {

            foreach (Lijek l in GlobalneVarijable.lijekovi)
            {
                if (l.naziv == nazivLijeka_t.Text)
                {
                    spisakLijekova_l.Items.Add(nazivLijeka_t.Text);
                    GlobalneVarijable.preporukaZaPenzioner.Add(nazivLijeka_t.Text);
                    nazivLijeka_t.Text = "";
                    return;
                }
            }
            MessageBox.Show("Ne postoji lijek koji ste unijeli!", "Upozorenje", MessageBoxButtons.OK,
                        MessageBoxIcon.Error);
            nazivLijeka_t.Text = "";
            return;        
        }

        private void obrisi_b_Click(object sender, EventArgs e)
        {
            GlobalneVarijable.preporukaZaPenzioner.Remove(GlobalneVarijable.preporukaZaPenzioner.Find(x => x == spisakLijekova_l.SelectedItem.ToString()));
            spisakLijekova_l.Items.Remove(spisakLijekova_l.SelectedItem);
        }


    }
}
