function [R] = erozija(slika, s)

v = size(slika);
a = ones(1, size(s,1) * size(s,2));

if mod(size(s,1), 2) == 1
    p = floor(size(s,1) / 2);
    for i = p + 1:1:v(1) - p
        for j = p + 1:1:v(2) - p
            brojac = 1;
            k = 1;
            for m = i - p:1:i + p
                for n = j - p:1:j + p
                    a(k) = s(brojac) * slika(m,n);
                    brojac = brojac + 1;
                    k = k + 1;
                end
            end
            R(i,j) = min(a);
        end
    end
else
    p = size(s,1);
    for i = p:1:v(1) - (p - 1)
        for j = p:1:v(2) - (p - 1)
            brojac = 1;
            k = 1;
            for m = i - (p - 1):1:i
                for n = j - (p - 1):1:j
                    a(k) = s(brojac) * slika(m,n);
                    brojac = brojac + 1;
                    k = k + 1;
                end
            end
            R(i,j) = min(a);
        end
    end 
end

end