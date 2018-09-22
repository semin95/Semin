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
    public partial class PreporukaIspis : Form
    {
        public PreporukaIspis()
        {
            InitializeComponent();
            foreach(string s in GlobalneVarijable.preporukaZaPenzioner)
            {
                preporukaLijekova_l.Items.Add(s);
            }
            if (GlobalneVarijable.preporukaZaPenzioner.Count() == 0)
                preporukaLijekova_l.Items.Add("Nema ni jedne preporuke!");
        }

        private void ok_b_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
