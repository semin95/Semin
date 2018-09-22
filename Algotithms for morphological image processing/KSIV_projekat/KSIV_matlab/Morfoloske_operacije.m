function varargout = Morfoloske_operacije(varargin)
% MORFOLOSKE_OPERACIJE MATLAB code for Morfoloske_operacije.fig
%      MORFOLOSKE_OPERACIJE, by itself, creates a new MORFOLOSKE_OPERACIJE or raises the existing
%      singleton*.
%
%      H = MORFOLOSKE_OPERACIJE returns the handle to a new MORFOLOSKE_OPERACIJE or the handle to
%      the existing singleton*.
%
%      MORFOLOSKE_OPERACIJE('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in MORFOLOSKE_OPERACIJE.M with the given input arguments.
%
%      MORFOLOSKE_OPERACIJE('Property','Value',...) creates a new MORFOLOSKE_OPERACIJE or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before Morfoloske_operacije_OpeningFcn gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to Morfoloske_operacije_OpeningFcn via varargin.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help Morfoloske_operacije

% Last Modified by GUIDE v2.5 20-May-2018 16:03:55

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @Morfoloske_operacije_OpeningFcn, ...
                   'gui_OutputFcn',  @Morfoloske_operacije_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT


% --- Executes just before Morfoloske_operacije is made visible.
function Morfoloske_operacije_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to Morfoloske_operacije (see VARARGIN)

% Choose default command line output for Morfoloske_operacije
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);

% UIWAIT makes Morfoloske_operacije wait for user response (see UIRESUME)
% uiwait(handles.figure1);


% --- Outputs from this function are returned to the command line.
function varargout = Morfoloske_operacije_OutputFcn(hObject, eventdata, handles) 
% varargout  cell array for returning output args (see VARARGOUT);
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get default command line output from handles structure
varargout{1} = handles.output;


% --- Executes on button press in pushbutton1.
function pushbutton1_Callback(hObject, eventdata, handles)
% hObject    handle to pushbutton1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

[a b] = uigetfile({'*.*', 'All Files'});
im = imread([b a]);
imshow(im, 'Parent', handles.axes2);


% --------------------------------------------------------------------
function Untitled_1_Callback(hObject, eventdata, handles)
% hObject    handle to Untitled_1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)



function edit2_Callback(hObject, eventdata, handles)
% hObject    handle to n (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of n as text
%        str2double(get(hObject,'String')) returns contents of n as a double


% --- Executes during object creation, after setting all properties.
function edit2_CreateFcn(hObject, eventdata, handles)
% hObject    handle to n (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on selection change in n.
function n_Callback(hObject, eventdata, handles)
% hObject    handle to n (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: contents = cellstr(get(hObject,'String')) returns n contents as cell array
%        contents{get(hObject,'Value')} returns selected item from n


% --- Executes during object creation, after setting all properties.
function n_CreateFcn(hObject, eventdata, handles)
% hObject    handle to n (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: popupmenu controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in dilation.
function dilation_Callback(hObject, eventdata, handles)
% hObject    handle to dilation (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
slika = getimage(handles.axes2);
s = str2num(get(handles.matrix, 'String'));
[R] = dilatacija(slika, s);
imshow(R, 'Parent', handles.axes4);
set(handles.rezultat, 'String', 'Dilatacija');


% --- Executes on button press in erosion.
function erosion_Callback(hObject, eventdata, handles)
% hObject    handle to erosion (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
slika = getimage(handles.axes2);
s = str2num(get(handles.matrix, 'String'));
[R] = erozija(slika, s);
imshow(R, 'Parent', handles.axes4);
set(handles.rezultat, 'String', 'Erozija');

% --- Executes on button press in openingandclosure.
function openingandclosure_Callback(hObject, eventdata, handles)
% hObject    handle to openingandclosure (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)



function matrix_Callback(hObject, eventdata, handles)
% hObject    handle to matrix (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of matrix as text
%        str2double(get(hObject,'String')) returns contents of matrix as a double


% --- Executes during object creation, after setting all properties.
function matrix_CreateFcn(hObject, eventdata, handles)
% hObject    handle to matrix (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function dimension_Callback(hObject, eventdata, handles)
% hObject    handle to dimension (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of dimension as text
%        str2double(get(hObject,'String')) returns contents of dimension as a double


% --- Executes during object creation, after setting all properties.
function dimension_CreateFcn(hObject, eventdata, handles)
% hObject    handle to dimension (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in generate.
function generate_Callback(hObject, eventdata, handles)
% hObject    handle to generate (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

n = str2num(get(handles.dimension, 'String'));
matrix = ones(n);
set(handles.matrix, 'String', num2str(matrix));


% --- Executes on button press in opening.
function opening_Callback(hObject, eventdata, handles)
% hObject    handle to opening (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
slika = getimage(handles.axes2);
s = str2num(get(handles.matrix, 'String'));
[R] = erozija(slika, s);
[R] = dilatacija(R, s);
imshow(R, 'Parent', handles.axes4);
set(handles.rezultat, 'String', 'Otvaranje');


% --- Executes on button press in closure.
function closure_Callback(hObject, eventdata, handles)
% hObject    handle to closure (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
slika = getimage(handles.axes2);
s = str2num(get(handles.matrix, 'String'));
[R] = dilatacija(slika, s);
[R] = erozija(R, s);
imshow(R, 'Parent', handles.axes4);
set(handles.rezultat, 'String', 'Zatvaranje');
