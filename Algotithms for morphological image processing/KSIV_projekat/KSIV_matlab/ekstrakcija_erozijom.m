function [R] = ekstrakcija_erozijom(slika, s)
    R = slika - erozija(slika, s);
end