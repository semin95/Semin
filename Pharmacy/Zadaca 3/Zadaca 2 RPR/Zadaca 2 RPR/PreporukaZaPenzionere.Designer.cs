namespace Zadaca_2_RPR
{
    partial class PreporukaZaPenzionere
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
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.obrisi_b = new System.Windows.Forms.Button();
            this.unesi_b = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.spisakLijekova_l = new System.Windows.Forms.ListBox();
            this.nazivLijeka_t = new System.Windows.Forms.TextBox();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.obrisi_b);
            this.groupBox1.Controls.Add(this.unesi_b);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.spisakLijekova_l);
            this.groupBox1.Controls.Add(this.nazivLijeka_t);
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(303, 159);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Preporuka lijekova za penzionere:";
            // 
            // obrisi_b
            // 
            this.obrisi_b.Location = new System.Drawing.Point(201, 120);
            this.obrisi_b.Name = "obrisi_b";
            this.obrisi_b.Size = new System.Drawing.Size(75, 23);
            this.obrisi_b.TabIndex = 4;
            this.obrisi_b.Text = "Obrisi";
            this.obrisi_b.UseVisualStyleBackColor = true;
            this.obrisi_b.Click += new System.EventHandler(this.obrisi_b_Click);
            // 
            // unesi_b
            // 
            this.unesi_b.Location = new System.Drawing.Point(44, 93);
            this.unesi_b.Name = "unesi_b";
            this.unesi_b.Size = new System.Drawing.Size(75, 23);
            this.unesi_b.TabIndex = 3;
            this.unesi_b.Text = "Unesi";
            this.unesi_b.UseVisualStyleBackColor = true;
            this.unesi_b.Click += new System.EventHandler(this.unesi_b_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(24, 29);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(64, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Naziv lijeka:";
            // 
            // spisakLijekova_l
            // 
            this.spisakLijekova_l.FormattingEnabled = true;
            this.spisakLijekova_l.Location = new System.Drawing.Point(177, 19);
            this.spisakLijekova_l.Name = "spisakLijekova_l";
            this.spisakLijekova_l.Size = new System.Drawing.Size(120, 95);
            this.spisakLijekova_l.TabIndex = 1;
            // 
            // nazivLijeka_t
            // 
            this.nazivLijeka_t.Location = new System.Drawing.Point(27, 45);
            this.nazivLijeka_t.Name = "nazivLijeka_t";
            this.nazivLijeka_t.Size = new System.Drawing.Size(115, 20);
            this.nazivLijeka_t.TabIndex = 0;
            // 
            // PreporukaZaPenzionere
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(327, 182);
            this.Controls.Add(this.groupBox1);
            this.Name = "PreporukaZaPenzionere";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "PreporukaZaPenzionere";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.ListBox spisakLijekova_l;
        private System.Windows.Forms.TextBox nazivLijeka_t;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button obrisi_b;
        private System.Windows.Forms.Button unesi_b;
    }
}