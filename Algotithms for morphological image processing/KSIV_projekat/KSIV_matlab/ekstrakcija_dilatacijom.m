function [R] = ekstrakcija_dilatacijom(slika, s)
    R = dilatacija(slika, s) - slika;
end