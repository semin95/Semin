namespace Zadaca_2_RPR
{
    partial class PreporukaIspis
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
            this.preporukaLijekova_g = new System.Windows.Forms.GroupBox();
            this.ok_b = new System.Windows.Forms.Button();
            this.preporukaLijekova_l = new System.Windows.Forms.ListBox();
            this.preporukaLijekova_g.SuspendLayout();
            this.SuspendLayout();
            // 
            // preporukaLijekova_g
            // 
            this.preporukaLijekova_g.Controls.Add(this.ok_b);
            this.preporukaLijekova_g.Controls.Add(this.preporukaLijekova_l);
            this.preporukaLijekova_g.Location = new System.Drawing.Point(13, 13);
            this.preporukaLijekova_g.Name = "preporukaLijekova_g";
            this.preporukaLijekova_g.Size = new System.Drawing.Size(194, 160);
            this.preporukaLijekova_g.TabIndex = 0;
            this.preporukaLijekova_g.TabStop = false;
            this.preporukaLijekova_g.Text = "Preporuka lijekova:";
            // 
            // ok_b
            // 
            this.ok_b.Location = new System.Drawing.Point(57, 131);
            this.ok_b.Name = "ok_b";
            this.ok_b.Size = new System.Drawing.Size(75, 23);
            this.ok_b.TabIndex = 1;
            this.ok_b.Text = "OK";
            this.ok_b.UseVisualStyleBackColor = true;
            this.ok_b.Click += new System.EventHandler(this.ok_b_Click);
            // 
            // preporukaLijekova_l
            // 
            this.preporukaLijekova_l.FormattingEnabled = true;
            this.preporukaLijekova_l.Location = new System.Drawing.Point(6, 16);
            this.preporukaLijekova_l.Name = "preporukaLijekova_l";
            this.preporukaLijekova_l.Size = new System.Drawing.Size(182, 95);
            this.preporukaLijekova_l.TabIndex = 0;
            // 
            // PreporukaIspis
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(223, 185);
            this.Controls.Add(this.preporukaLijekova_g);
            this.Name = "PreporukaIspis";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "PreporukaIspis";
            this.preporukaLijekova_g.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox preporukaLijekova_g;
        private System.Windows.Forms.Button ok_b;
        private System.Windows.Forms.ListBox preporukaLijekova_l;
    }
}