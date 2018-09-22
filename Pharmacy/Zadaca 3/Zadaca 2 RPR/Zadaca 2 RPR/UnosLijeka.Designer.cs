namespace Zadaca_2_RPR
{
    partial class UnosLijeka
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
            this.lijek_g = new System.Windows.Forms.GroupBox();
            this.statusStrip1 = new System.Windows.Forms.StatusStrip();
            this.toolStripStatusLabel1 = new System.Windows.Forms.ToolStripStatusLabel();
            this.statusna_linija = new System.Windows.Forms.ToolStripStatusLabel();
            this.maxUnosZaDjecu_n = new System.Windows.Forms.NumericUpDown();
            this.label1 = new System.Windows.Forms.Label();
            this.unesiLijek_b = new System.Windows.Forms.Button();
            this.sastojci_g = new System.Windows.Forms.GroupBox();
            this.sastojci_l = new System.Windows.Forms.ListBox();
            this.unesiSastojak_b = new System.Windows.Forms.Button();
            this.kolicina_n = new System.Windows.Forms.NumericUpDown();
            this.sastojak_t = new System.Windows.Forms.TextBox();
            this.kategorija_g = new System.Windows.Forms.GroupBox();
            this.ne_r = new System.Windows.Forms.RadioButton();
            this.da_r = new System.Windows.Forms.RadioButton();
            this.pokrivenostOsiguranja_g = new System.Windows.Forms.GroupBox();
            this.dvadeset_r = new System.Windows.Forms.RadioButton();
            this.deset_r = new System.Windows.Forms.RadioButton();
            this.dostupnaKolicina_l = new System.Windows.Forms.Label();
            this.dostupnaKolicina_n = new System.Windows.Forms.NumericUpDown();
            this.doza_l = new System.Windows.Forms.Label();
            this.doza_n = new System.Windows.Forms.NumericUpDown();
            this.cijena_t = new System.Windows.Forms.TextBox();
            this.cijena_l = new System.Windows.Forms.Label();
            this.tipLijeka_l = new System.Windows.Forms.Label();
            this.formaLijeka_l = new System.Windows.Forms.Label();
            this.tipLijeka_t = new System.Windows.Forms.TextBox();
            this.formaLijeka_t = new System.Windows.Forms.TextBox();
            this.naziv_t = new System.Windows.Forms.TextBox();
            this.naziv_l = new System.Windows.Forms.Label();
            this.error = new System.Windows.Forms.ErrorProvider(this.components);
            this.lijek_g.SuspendLayout();
            this.statusStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.maxUnosZaDjecu_n)).BeginInit();
            this.sastojci_g.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.kolicina_n)).BeginInit();
            this.kategorija_g.SuspendLayout();
            this.pokrivenostOsiguranja_g.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dostupnaKolicina_n)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.doza_n)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.error)).BeginInit();
            this.SuspendLayout();
            // 
            // lijek_g
            // 
            this.lijek_g.Controls.Add(this.statusStrip1);
            this.lijek_g.Controls.Add(this.maxUnosZaDjecu_n);
            this.lijek_g.Controls.Add(this.label1);
            this.lijek_g.Controls.Add(this.unesiLijek_b);
            this.lijek_g.Controls.Add(this.sastojci_g);
            this.lijek_g.Controls.Add(this.kategorija_g);
            this.lijek_g.Controls.Add(this.pokrivenostOsiguranja_g);
            this.lijek_g.Controls.Add(this.dostupnaKolicina_l);
            this.lijek_g.Controls.Add(this.dostupnaKolicina_n);
            this.lijek_g.Controls.Add(this.doza_l);
            this.lijek_g.Controls.Add(this.doza_n);
            this.lijek_g.Controls.Add(this.cijena_t);
            this.lijek_g.Controls.Add(this.cijena_l);
            this.lijek_g.Controls.Add(this.tipLijeka_l);
            this.lijek_g.Controls.Add(this.formaLijeka_l);
            this.lijek_g.Controls.Add(this.tipLijeka_t);
            this.lijek_g.Controls.Add(this.formaLijeka_t);
            this.lijek_g.Controls.Add(this.naziv_t);
            this.lijek_g.Controls.Add(this.naziv_l);
            this.lijek_g.Location = new System.Drawing.Point(12, 12);
            this.lijek_g.Name = "lijek_g";
            this.lijek_g.Size = new System.Drawing.Size(294, 470);
            this.lijek_g.TabIndex = 1;
            this.lijek_g.TabStop = false;
            this.lijek_g.Text = "Lijek";
            // 
            // statusStrip1
            // 
            this.statusStrip1.BackColor = System.Drawing.Color.DarkSeaGreen;
            this.statusStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripStatusLabel1,
            this.statusna_linija});
            this.statusStrip1.Location = new System.Drawing.Point(3, 445);
            this.statusStrip1.Name = "statusStrip1";
            this.statusStrip1.Size = new System.Drawing.Size(288, 22);
            this.statusStrip1.TabIndex = 19;
            this.statusStrip1.Text = "FDSFDSF";
            // 
            // toolStripStatusLabel1
            // 
            this.toolStripStatusLabel1.Name = "toolStripStatusLabel1";
            this.toolStripStatusLabel1.Size = new System.Drawing.Size(0, 17);
            // 
            // statusna_linija
            // 
            this.statusna_linija.ForeColor = System.Drawing.Color.Red;
            this.statusna_linija.Name = "statusna_linija";
            this.statusna_linija.Size = new System.Drawing.Size(0, 17);
            // 
            // maxUnosZaDjecu_n
            // 
            this.maxUnosZaDjecu_n.BackColor = System.Drawing.Color.Gainsboro;
            this.maxUnosZaDjecu_n.Location = new System.Drawing.Point(143, 197);
            this.maxUnosZaDjecu_n.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.maxUnosZaDjecu_n.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.maxUnosZaDjecu_n.Name = "maxUnosZaDjecu_n";
            this.maxUnosZaDjecu_n.Size = new System.Drawing.Size(120, 20);
            this.maxUnosZaDjecu_n.TabIndex = 18;
            this.maxUnosZaDjecu_n.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(31, 199);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(99, 13);
            this.label1.TabIndex = 17;
            this.label1.Text = "Max unos za djecu:";
            // 
            // unesiLijek_b
            // 
            this.unesiLijek_b.BackColor = System.Drawing.Color.LightBlue;
            this.unesiLijek_b.Location = new System.Drawing.Point(188, 418);
            this.unesiLijek_b.Name = "unesiLijek_b";
            this.unesiLijek_b.Size = new System.Drawing.Size(75, 23);
            this.unesiLijek_b.TabIndex = 16;
            this.unesiLijek_b.Text = "Unesi lijek";
            this.unesiLijek_b.UseVisualStyleBackColor = false;
            this.unesiLijek_b.Click += new System.EventHandler(this.unesiLijek_b_Click);
            // 
            // sastojci_g
            // 
            this.sastojci_g.Controls.Add(this.sastojci_l);
            this.sastojci_g.Controls.Add(this.unesiSastojak_b);
            this.sastojci_g.Controls.Add(this.kolicina_n);
            this.sastojci_g.Controls.Add(this.sastojak_t);
            this.sastojci_g.Location = new System.Drawing.Point(33, 324);
            this.sastojci_g.Name = "sastojci_g";
            this.sastojci_g.Size = new System.Drawing.Size(230, 88);
            this.sastojci_g.TabIndex = 15;
            this.sastojci_g.TabStop = false;
            this.sastojci_g.Text = "Sastojak i kolicina sastojka:";
            // 
            // sastojci_l
            // 
            this.sastojci_l.BackColor = System.Drawing.Color.Gainsboro;
            this.sastojci_l.FormattingEnabled = true;
            this.sastojci_l.Location = new System.Drawing.Point(124, 19);
            this.sastojci_l.Name = "sastojci_l";
            this.sastojci_l.ScrollAlwaysVisible = true;
            this.sastojci_l.Size = new System.Drawing.Size(108, 30);
            this.sastojci_l.TabIndex = 7;
            // 
            // unesiSastojak_b
            // 
            this.unesiSastojak_b.Location = new System.Drawing.Point(153, 55);
            this.unesiSastojak_b.Name = "unesiSastojak_b";
            this.unesiSastojak_b.Size = new System.Drawing.Size(51, 23);
            this.unesiSastojak_b.TabIndex = 6;
            this.unesiSastojak_b.Text = "Unesi sastojak";
            this.unesiSastojak_b.UseVisualStyleBackColor = true;
            this.unesiSastojak_b.Click += new System.EventHandler(this.unesiSastojak_b_Click);
            // 
            // kolicina_n
            // 
            this.kolicina_n.BackColor = System.Drawing.Color.Gainsboro;
            this.kolicina_n.Location = new System.Drawing.Point(43, 55);
            this.kolicina_n.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.kolicina_n.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.kolicina_n.Name = "kolicina_n";
            this.kolicina_n.Size = new System.Drawing.Size(36, 20);
            this.kolicina_n.TabIndex = 2;
            this.kolicina_n.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // sastojak_t
            // 
            this.sastojak_t.BackColor = System.Drawing.Color.Gainsboro;
            this.sastojak_t.Location = new System.Drawing.Point(22, 19);
            this.sastojak_t.Name = "sastojak_t";
            this.sastojak_t.Size = new System.Drawing.Size(84, 20);
            this.sastojak_t.TabIndex = 0;
            // 
            // kategorija_g
            // 
            this.kategorija_g.Controls.Add(this.ne_r);
            this.kategorija_g.Controls.Add(this.da_r);
            this.kategorija_g.Location = new System.Drawing.Point(33, 271);
            this.kategorija_g.Name = "kategorija_g";
            this.kategorija_g.Size = new System.Drawing.Size(230, 47);
            this.kategorija_g.TabIndex = 14;
            this.kategorija_g.TabStop = false;
            this.kategorija_g.Text = "Kategorija(recept):";
            // 
            // ne_r
            // 
            this.ne_r.AutoSize = true;
            this.ne_r.Cursor = System.Windows.Forms.Cursors.Hand;
            this.ne_r.Location = new System.Drawing.Point(139, 19);
            this.ne_r.Name = "ne_r";
            this.ne_r.Size = new System.Drawing.Size(40, 17);
            this.ne_r.TabIndex = 1;
            this.ne_r.TabStop = true;
            this.ne_r.Text = "NE";
            this.ne_r.UseVisualStyleBackColor = true;
            // 
            // da_r
            // 
            this.da_r.AutoSize = true;
            this.da_r.Cursor = System.Windows.Forms.Cursors.Hand;
            this.da_r.Location = new System.Drawing.Point(47, 19);
            this.da_r.Name = "da_r";
            this.da_r.Size = new System.Drawing.Size(40, 17);
            this.da_r.TabIndex = 0;
            this.da_r.TabStop = true;
            this.da_r.Text = "DA";
            this.da_r.UseVisualStyleBackColor = true;
            // 
            // pokrivenostOsiguranja_g
            // 
            this.pokrivenostOsiguranja_g.Controls.Add(this.dvadeset_r);
            this.pokrivenostOsiguranja_g.Controls.Add(this.deset_r);
            this.pokrivenostOsiguranja_g.Location = new System.Drawing.Point(33, 218);
            this.pokrivenostOsiguranja_g.Name = "pokrivenostOsiguranja_g";
            this.pokrivenostOsiguranja_g.Size = new System.Drawing.Size(230, 47);
            this.pokrivenostOsiguranja_g.TabIndex = 13;
            this.pokrivenostOsiguranja_g.TabStop = false;
            this.pokrivenostOsiguranja_g.Text = "Pokrivenost osiguranja:";
            // 
            // dvadeset_r
            // 
            this.dvadeset_r.AutoSize = true;
            this.dvadeset_r.Cursor = System.Windows.Forms.Cursors.Hand;
            this.dvadeset_r.Location = new System.Drawing.Point(139, 19);
            this.dvadeset_r.Name = "dvadeset_r";
            this.dvadeset_r.Size = new System.Drawing.Size(45, 17);
            this.dvadeset_r.TabIndex = 1;
            this.dvadeset_r.TabStop = true;
            this.dvadeset_r.Text = "20%";
            this.dvadeset_r.UseVisualStyleBackColor = true;
            // 
            // deset_r
            // 
            this.deset_r.AutoSize = true;
            this.deset_r.BackColor = System.Drawing.Color.LightBlue;
            this.deset_r.Cursor = System.Windows.Forms.Cursors.Hand;
            this.deset_r.Location = new System.Drawing.Point(47, 19);
            this.deset_r.Name = "deset_r";
            this.deset_r.Size = new System.Drawing.Size(45, 17);
            this.deset_r.TabIndex = 0;
            this.deset_r.TabStop = true;
            this.deset_r.Text = "10%";
            this.deset_r.UseVisualStyleBackColor = false;
            // 
            // dostupnaKolicina_l
            // 
            this.dostupnaKolicina_l.AutoSize = true;
            this.dostupnaKolicina_l.Location = new System.Drawing.Point(30, 171);
            this.dostupnaKolicina_l.Name = "dostupnaKolicina_l";
            this.dostupnaKolicina_l.Size = new System.Drawing.Size(95, 13);
            this.dostupnaKolicina_l.TabIndex = 12;
            this.dostupnaKolicina_l.Text = "Dostupna kolicina:";
            // 
            // dostupnaKolicina_n
            // 
            this.dostupnaKolicina_n.BackColor = System.Drawing.Color.Gainsboro;
            this.dostupnaKolicina_n.Location = new System.Drawing.Point(143, 169);
            this.dostupnaKolicina_n.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.dostupnaKolicina_n.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.dostupnaKolicina_n.Name = "dostupnaKolicina_n";
            this.dostupnaKolicina_n.Size = new System.Drawing.Size(120, 20);
            this.dostupnaKolicina_n.TabIndex = 11;
            this.dostupnaKolicina_n.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // doza_l
            // 
            this.doza_l.AutoSize = true;
            this.doza_l.Location = new System.Drawing.Point(30, 139);
            this.doza_l.Name = "doza_l";
            this.doza_l.Size = new System.Drawing.Size(35, 13);
            this.doza_l.TabIndex = 10;
            this.doza_l.Text = "Doza:";
            // 
            // doza_n
            // 
            this.doza_n.BackColor = System.Drawing.Color.Gainsboro;
            this.doza_n.Location = new System.Drawing.Point(143, 137);
            this.doza_n.Maximum = new decimal(new int[] {
            10000000,
            0,
            0,
            0});
            this.doza_n.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.doza_n.Name = "doza_n";
            this.doza_n.Size = new System.Drawing.Size(120, 20);
            this.doza_n.TabIndex = 9;
            this.doza_n.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // cijena_t
            // 
            this.cijena_t.BackColor = System.Drawing.Color.Gainsboro;
            this.cijena_t.Location = new System.Drawing.Point(143, 107);
            this.cijena_t.Name = "cijena_t";
            this.cijena_t.Size = new System.Drawing.Size(120, 20);
            this.cijena_t.TabIndex = 8;
            this.cijena_t.TextChanged += new System.EventHandler(this.cijena_t_TextChanged);
            // 
            // cijena_l
            // 
            this.cijena_l.AutoSize = true;
            this.cijena_l.Location = new System.Drawing.Point(30, 110);
            this.cijena_l.Name = "cijena_l";
            this.cijena_l.Size = new System.Drawing.Size(64, 13);
            this.cijena_l.TabIndex = 7;
            this.cijena_l.Text = "Cijena(KM) :";
            // 
            // tipLijeka_l
            // 
            this.tipLijeka_l.AutoSize = true;
            this.tipLijeka_l.Location = new System.Drawing.Point(30, 81);
            this.tipLijeka_l.Name = "tipLijeka_l";
            this.tipLijeka_l.Size = new System.Drawing.Size(52, 13);
            this.tipLijeka_l.TabIndex = 5;
            this.tipLijeka_l.Text = "Tip lijeka:";
            // 
            // formaLijeka_l
            // 
            this.formaLijeka_l.AutoSize = true;
            this.formaLijeka_l.Location = new System.Drawing.Point(31, 51);
            this.formaLijeka_l.Name = "formaLijeka_l";
            this.formaLijeka_l.Size = new System.Drawing.Size(66, 13);
            this.formaLijeka_l.TabIndex = 4;
            this.formaLijeka_l.Text = "Forma lijeka:";
            // 
            // tipLijeka_t
            // 
            this.tipLijeka_t.BackColor = System.Drawing.Color.Gainsboro;
            this.tipLijeka_t.Location = new System.Drawing.Point(143, 78);
            this.tipLijeka_t.Name = "tipLijeka_t";
            this.tipLijeka_t.Size = new System.Drawing.Size(120, 20);
            this.tipLijeka_t.TabIndex = 3;
            this.tipLijeka_t.TextChanged += new System.EventHandler(this.tipLijeka_t_TextChanged);
            // 
            // formaLijeka_t
            // 
            this.formaLijeka_t.BackColor = System.Drawing.Color.Gainsboro;
            this.formaLijeka_t.Location = new System.Drawing.Point(143, 48);
            this.formaLijeka_t.Name = "formaLijeka_t";
            this.formaLijeka_t.Size = new System.Drawing.Size(120, 20);
            this.formaLijeka_t.TabIndex = 2;
            this.formaLijeka_t.TextChanged += new System.EventHandler(this.formaLijeka_t_TextChanged);
            // 
            // naziv_t
            // 
            this.naziv_t.BackColor = System.Drawing.Color.Gainsboro;
            this.naziv_t.Location = new System.Drawing.Point(143, 18);
            this.naziv_t.Name = "naziv_t";
            this.naziv_t.Size = new System.Drawing.Size(120, 20);
            this.naziv_t.TabIndex = 1;
            this.naziv_t.TextChanged += new System.EventHandler(this.naziv_t_TextChanged);
            // 
            // naziv_l
            // 
            this.naziv_l.AutoSize = true;
            this.naziv_l.Location = new System.Drawing.Point(30, 21);
            this.naziv_l.Name = "naziv_l";
            this.naziv_l.Size = new System.Drawing.Size(37, 13);
            this.naziv_l.TabIndex = 0;
            this.naziv_l.Text = "Naziv:";
            // 
            // error
            // 
            this.error.ContainerControl = this;
            // 
            // UnosLijeka
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightBlue;
            this.ClientSize = new System.Drawing.Size(323, 488);
            this.Controls.Add(this.lijek_g);
            this.Name = "UnosLijeka";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "UnosLijeka";
            this.Load += new System.EventHandler(this.UnosLijeka_Load);
            this.lijek_g.ResumeLayout(false);
            this.lijek_g.PerformLayout();
            this.statusStrip1.ResumeLayout(false);
            this.statusStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.maxUnosZaDjecu_n)).EndInit();
            this.sastojci_g.ResumeLayout(false);
            this.sastojci_g.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.kolicina_n)).EndInit();
            this.kategorija_g.ResumeLayout(false);
            this.kategorija_g.PerformLayout();
            this.pokrivenostOsiguranja_g.ResumeLayout(false);
            this.pokrivenostOsiguranja_g.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dostupnaKolicina_n)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.doza_n)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.error)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox lijek_g;
        private System.Windows.Forms.Button unesiLijek_b;
        private System.Windows.Forms.GroupBox sastojci_g;
        private System.Windows.Forms.ListBox sastojci_l;
        private System.Windows.Forms.Button unesiSastojak_b;
        private System.Windows.Forms.NumericUpDown kolicina_n;
        private System.Windows.Forms.TextBox sastojak_t;
        private System.Windows.Forms.GroupBox kategorija_g;
        private System.Windows.Forms.RadioButton ne_r;
        private System.Windows.Forms.RadioButton da_r;
        private System.Windows.Forms.GroupBox pokrivenostOsiguranja_g;
        private System.Windows.Forms.RadioButton dvadeset_r;
        private System.Windows.Forms.RadioButton deset_r;
        private System.Windows.Forms.Label dostupnaKolicina_l;
        private System.Windows.Forms.NumericUpDown dostupnaKolicina_n;
        private System.Windows.Forms.Label doza_l;
        private System.Windows.Forms.NumericUpDown doza_n;
        private System.Windows.Forms.TextBox cijena_t;
        private System.Windows.Forms.Label cijena_l;
        private System.Windows.Forms.Label tipLijeka_l;
        private System.Windows.Forms.Label formaLijeka_l;
        private System.Windows.Forms.TextBox tipLijeka_t;
        private System.Windows.Forms.TextBox formaLijeka_t;
        private System.Windows.Forms.TextBox naziv_t;
        private System.Windows.Forms.Label naziv_l;
        private System.Windows.Forms.NumericUpDown maxUnosZaDjecu_n;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ErrorProvider error;
        private System.Windows.Forms.StatusStrip statusStrip1;
        private System.Windows.Forms.ToolStripStatusLabel toolStripStatusLabel1;
        private System.Windows.Forms.ToolStripStatusLabel statusna_linija;
    }
}