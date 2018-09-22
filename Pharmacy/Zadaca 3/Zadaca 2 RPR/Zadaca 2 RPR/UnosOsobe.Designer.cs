namespace Zadaca_2_RPR
{
    partial class UnosOsobe
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.osoba_g = new System.Windows.Forms.GroupBox();
            this.statusStrip1 = new System.Windows.Forms.StatusStrip();
            this.statusna_linija = new System.Windows.Forms.ToolStripStatusLabel();
            this.unesiKupca_b = new System.Windows.Forms.Button();
            this.listaAlergija_g = new System.Windows.Forms.GroupBox();
            this.listaAlergija_b = new System.Windows.Forms.Button();
            this.listaAlergija_l = new System.Windows.Forms.ListBox();
            this.listaAlergija_t = new System.Windows.Forms.TextBox();
            this.unesiProdavaca_b = new System.Windows.Forms.Button();
            this.datumRodjenja_l = new System.Windows.Forms.Label();
            this.datumRodjenja_d = new System.Windows.Forms.DateTimePicker();
            this.id_l = new System.Windows.Forms.Label();
            this.id_n = new System.Windows.Forms.NumericUpDown();
            this.prezime_t = new System.Windows.Forms.TextBox();
            this.prezime_l = new System.Windows.Forms.Label();
            this.ime_t = new System.Windows.Forms.TextBox();
            this.ime_l = new System.Windows.Forms.Label();
            this.izbor_c = new System.Windows.Forms.ComboBox();
            this.error = new System.Windows.Forms.ErrorProvider(this.components);
            this.osoba_g.SuspendLayout();
            this.statusStrip1.SuspendLayout();
            this.listaAlergija_g.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.id_n)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.error)).BeginInit();
            this.SuspendLayout();
            // 
            // osoba_g
            // 
            this.osoba_g.Controls.Add(this.statusStrip1);
            this.osoba_g.Controls.Add(this.unesiKupca_b);
            this.osoba_g.Controls.Add(this.listaAlergija_g);
            this.osoba_g.Controls.Add(this.unesiProdavaca_b);
            this.osoba_g.Controls.Add(this.datumRodjenja_l);
            this.osoba_g.Controls.Add(this.datumRodjenja_d);
            this.osoba_g.Controls.Add(this.id_l);
            this.osoba_g.Controls.Add(this.id_n);
            this.osoba_g.Controls.Add(this.prezime_t);
            this.osoba_g.Controls.Add(this.prezime_l);
            this.osoba_g.Controls.Add(this.ime_t);
            this.osoba_g.Controls.Add(this.ime_l);
            this.osoba_g.Controls.Add(this.izbor_c);
            this.osoba_g.Location = new System.Drawing.Point(25, 12);
            this.osoba_g.Name = "osoba_g";
            this.osoba_g.Size = new System.Drawing.Size(377, 411);
            this.osoba_g.TabIndex = 1;
            this.osoba_g.TabStop = false;
            this.osoba_g.Text = "Osoba";
            // 
            // statusStrip1
            // 
            this.statusStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.statusna_linija});
            this.statusStrip1.Location = new System.Drawing.Point(3, 386);
            this.statusStrip1.Name = "statusStrip1";
            this.statusStrip1.Size = new System.Drawing.Size(371, 22);
            this.statusStrip1.TabIndex = 12;
            this.statusStrip1.Text = "statusStrip1";
            // 
            // statusna_linija
            // 
            this.statusna_linija.ForeColor = System.Drawing.Color.Red;
            this.statusna_linija.Name = "statusna_linija";
            this.statusna_linija.Size = new System.Drawing.Size(0, 17);
            // 
            // unesiKupca_b
            // 
            this.unesiKupca_b.Location = new System.Drawing.Point(164, 354);
            this.unesiKupca_b.Name = "unesiKupca_b";
            this.unesiKupca_b.Size = new System.Drawing.Size(75, 23);
            this.unesiKupca_b.TabIndex = 11;
            this.unesiKupca_b.Text = "Unesi kupca";
            this.unesiKupca_b.UseVisualStyleBackColor = true;
            this.unesiKupca_b.Visible = false;
            this.unesiKupca_b.Click += new System.EventHandler(this.unesiKupca_b_Click);
            // 
            // listaAlergija_g
            // 
            this.listaAlergija_g.Controls.Add(this.listaAlergija_b);
            this.listaAlergija_g.Controls.Add(this.listaAlergija_l);
            this.listaAlergija_g.Controls.Add(this.listaAlergija_t);
            this.listaAlergija_g.Location = new System.Drawing.Point(90, 222);
            this.listaAlergija_g.Name = "listaAlergija_g";
            this.listaAlergija_g.Size = new System.Drawing.Size(229, 115);
            this.listaAlergija_g.TabIndex = 10;
            this.listaAlergija_g.TabStop = false;
            this.listaAlergija_g.Text = "Lista alergija:";
            this.listaAlergija_g.Visible = false;
            // 
            // listaAlergija_b
            // 
            this.listaAlergija_b.Location = new System.Drawing.Point(28, 70);
            this.listaAlergija_b.Name = "listaAlergija_b";
            this.listaAlergija_b.Size = new System.Drawing.Size(50, 23);
            this.listaAlergija_b.TabIndex = 2;
            this.listaAlergija_b.Text = "Unesi";
            this.listaAlergija_b.UseVisualStyleBackColor = true;
            this.listaAlergija_b.Click += new System.EventHandler(this.listaAlergija_b_Click);
            // 
            // listaAlergija_l
            // 
            this.listaAlergija_l.FormattingEnabled = true;
            this.listaAlergija_l.Location = new System.Drawing.Point(109, 14);
            this.listaAlergija_l.Name = "listaAlergija_l";
            this.listaAlergija_l.Size = new System.Drawing.Size(104, 95);
            this.listaAlergija_l.Sorted = true;
            this.listaAlergija_l.TabIndex = 1;
            // 
            // listaAlergija_t
            // 
            this.listaAlergija_t.Location = new System.Drawing.Point(17, 33);
            this.listaAlergija_t.Name = "listaAlergija_t";
            this.listaAlergija_t.Size = new System.Drawing.Size(75, 20);
            this.listaAlergija_t.TabIndex = 0;
            // 
            // unesiProdavaca_b
            // 
            this.unesiProdavaca_b.Location = new System.Drawing.Point(143, 242);
            this.unesiProdavaca_b.Name = "unesiProdavaca_b";
            this.unesiProdavaca_b.Size = new System.Drawing.Size(121, 23);
            this.unesiProdavaca_b.TabIndex = 9;
            this.unesiProdavaca_b.Text = "Unesi prodavaca";
            this.unesiProdavaca_b.UseVisualStyleBackColor = true;
            this.unesiProdavaca_b.Visible = false;
            this.unesiProdavaca_b.Click += new System.EventHandler(this.unesiProdavaca_b_Click);
            // 
            // datumRodjenja_l
            // 
            this.datumRodjenja_l.AutoSize = true;
            this.datumRodjenja_l.Location = new System.Drawing.Point(87, 193);
            this.datumRodjenja_l.Name = "datumRodjenja_l";
            this.datumRodjenja_l.Size = new System.Drawing.Size(81, 13);
            this.datumRodjenja_l.TabIndex = 8;
            this.datumRodjenja_l.Text = "Datum rodjenja:";
            // 
            // datumRodjenja_d
            // 
            this.datumRodjenja_d.Location = new System.Drawing.Point(207, 187);
            this.datumRodjenja_d.Name = "datumRodjenja_d";
            this.datumRodjenja_d.Size = new System.Drawing.Size(112, 20);
            this.datumRodjenja_d.TabIndex = 7;
            // 
            // id_l
            // 
            this.id_l.AutoSize = true;
            this.id_l.Location = new System.Drawing.Point(87, 149);
            this.id_l.Name = "id_l";
            this.id_l.Size = new System.Drawing.Size(21, 13);
            this.id_l.TabIndex = 6;
            this.id_l.Text = "ID:";
            // 
            // id_n
            // 
            this.id_n.Location = new System.Drawing.Point(207, 147);
            this.id_n.Name = "id_n";
            this.id_n.Size = new System.Drawing.Size(112, 20);
            this.id_n.TabIndex = 5;
            // 
            // prezime_t
            // 
            this.prezime_t.Location = new System.Drawing.Point(207, 103);
            this.prezime_t.Name = "prezime_t";
            this.prezime_t.Size = new System.Drawing.Size(112, 20);
            this.prezime_t.TabIndex = 4;
            // 
            // prezime_l
            // 
            this.prezime_l.AutoSize = true;
            this.prezime_l.Location = new System.Drawing.Point(87, 106);
            this.prezime_l.Name = "prezime_l";
            this.prezime_l.Size = new System.Drawing.Size(47, 13);
            this.prezime_l.TabIndex = 3;
            this.prezime_l.Text = "Prezime:";
            // 
            // ime_t
            // 
            this.ime_t.Location = new System.Drawing.Point(207, 61);
            this.ime_t.Name = "ime_t";
            this.ime_t.Size = new System.Drawing.Size(112, 20);
            this.ime_t.TabIndex = 2;
            // 
            // ime_l
            // 
            this.ime_l.AutoSize = true;
            this.ime_l.Location = new System.Drawing.Point(87, 64);
            this.ime_l.Name = "ime_l";
            this.ime_l.Size = new System.Drawing.Size(27, 13);
            this.ime_l.TabIndex = 1;
            this.ime_l.Text = "Ime:";
            // 
            // izbor_c
            // 
            this.izbor_c.FormattingEnabled = true;
            this.izbor_c.Items.AddRange(new object[] {
            "Dijete",
            "Obicni",
            "Penzioner",
            "Prodavac"});
            this.izbor_c.Location = new System.Drawing.Point(143, 19);
            this.izbor_c.Name = "izbor_c";
            this.izbor_c.Size = new System.Drawing.Size(121, 21);
            this.izbor_c.Sorted = true;
            this.izbor_c.TabIndex = 0;
            this.izbor_c.SelectedIndexChanged += new System.EventHandler(this.izbor_c_SelectedIndexChanged);
            // 
            // error
            // 
            this.error.ContainerControl = this;
            // 
            // UnosOsobe
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(427, 435);
            this.Controls.Add(this.osoba_g);
            this.Name = "UnosOsobe";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "UnosOsobe";
            this.osoba_g.ResumeLayout(false);
            this.osoba_g.PerformLayout();
            this.statusStrip1.ResumeLayout(false);
            this.statusStrip1.PerformLayout();
            this.listaAlergija_g.ResumeLayout(false);
            this.listaAlergija_g.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.id_n)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.error)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox osoba_g;
        private System.Windows.Forms.Button unesiKupca_b;
        private System.Windows.Forms.GroupBox listaAlergija_g;
        private System.Windows.Forms.Button listaAlergija_b;
        private System.Windows.Forms.ListBox listaAlergija_l;
        private System.Windows.Forms.TextBox listaAlergija_t;
        private System.Windows.Forms.Button unesiProdavaca_b;
        private System.Windows.Forms.Label datumRodjenja_l;
        private System.Windows.Forms.DateTimePicker datumRodjenja_d;
        private System.Windows.Forms.Label id_l;
        private System.Windows.Forms.NumericUpDown id_n;
        private System.Windows.Forms.TextBox prezime_t;
        private System.Windows.Forms.Label prezime_l;
        private System.Windows.Forms.TextBox ime_t;
        private System.Windows.Forms.Label ime_l;
        private System.Windows.Forms.ComboBox izbor_c;
        private System.Windows.Forms.ErrorProvider error;
        private System.Windows.Forms.StatusStrip statusStrip1;
        private System.Windows.Forms.ToolStripStatusLabel statusna_linija;
    }
}