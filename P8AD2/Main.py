from tkinter import *
import xml.etree.ElementTree as ET
from xml.etree.ElementTree import ElementTree
from tkinter.filedialog import askopenfilename
from tkinter import ttk
from tkinter import messagebox


app= Tk()

app.geometry("500x500")
app.title("Selector de opciones")
titulo = Label(app, text="Elije una opción", font=("Arial", 25))
titulo.pack()
texto = ""
cancion = []

def modNombre(fichero, arbol, ventana, idDisco, newNombre):
    idcorrecto = False
    
    for disco in arbol.findall('disco'):
        if disco.get('id') == idDisco:
            idcorrecto = True
            disco.set('nombre', newNombre)
            
    if(idcorrecto):
        messagebox.showinfo("Mod Nombre", "Nombre modificado correctamente")
    else:
        messagebox.showerror("Error", "El id introducido no corresponde con ningun disco")
    arbol.write(fichero)
    ventana.destroy()
    

def pregNombre(fichero, arbol, ventana):
    labelID = Label(ventana, text="Cual es el id del disco que quieres modificar")
    labelID.pack()
    idDisco = Entry(ventana, width=50)
    idDisco.pack()
    preguntaNombre = Label(ventana, text="Cual es el nuevo nombre del disco")
    preguntaNombre.pack()
    newNombre = Entry(ventana, width=50)
    newNombre.pack()
    botonEnviar = Button(ventana, text= "Modificar", command=lambda:[modNombre(fichero,arbol,ventana, idDisco.get(), newNombre.get()), labelID.destroy(), idDisco.destroy(), preguntaNombre.destroy(), newNombre.destroy(), botonEnviar.destroy()])
    botonEnviar.pack()

def modArtista(fichero,arbol,ventana, idDisco, newNombre):
    idcorrecto = False
    for disco in arbol.findall('disco'):
        if disco.get('id') == idDisco:
            idcorrecto = True
            disco.set('artista', newNombre)

    if(idcorrecto):
        messagebox.showinfo("Mod Nombre", "Nombre modificado correctamente")
    else:
        messagebox.showerror("Error", "El id introducido no corresponde con ningun disco")
    arbol.write(fichero)
    ventana.destroy()
    

def pregArtista(fichero , arbol, ventana):
    labelID = Label(ventana, text="Cual es el id del disco que quieres modificar")
    labelID.pack()
    idDisco = Entry(ventana, width=50)
    idDisco.pack()
    preguntaNombre = Label(ventana, text="Cual es el nuevo nombre de artista del disco")
    preguntaNombre.pack()
    newNombre = Entry(ventana, width=50)
    newNombre.pack()
    botonEnviar = Button(ventana, text= "Modificar", command=lambda:[modArtista(fichero,arbol,ventana, idDisco.get(), newNombre.get()), labelID.destroy(), idDisco.destroy(), preguntaNombre.destroy(), newNombre.destroy(), botonEnviar.destroy()])
    botonEnviar.pack()

def modGenero(fichero,arbol,ventana, idDisco, newNombre):
    idcorrecto = False
    for disco in arbol.findall('disco'):
        if disco.get('id') == idDisco:
            idcorrecto = True
            disco.set('genero', newNombre)
    if(idcorrecto):
        messagebox.showinfo("Mod Nombre", "Nombre modificado correctamente")
    else:
        messagebox.showerror("Error", "El id introducido no corresponde con ningun disco")
    arbol.write(fichero)
    ventana.destroy()

def pregGenero(fichero , arbol, ventana):
    labelID = Label(ventana, text="Cual es el id del disco que quieres modificar")
    labelID.pack()
    idDisco = Entry(ventana, width=50)
    idDisco.pack()
    preguntaNombre = Label(ventana, text="Cual es el nuevo nombre de artista del disco")
    preguntaNombre.pack()
    newNombre = Entry(ventana, width=50)
    newNombre.pack()
    botonEnviar = Button(ventana, text= "Modificar", command=lambda:[modGenero(fichero,arbol,ventana, idDisco.get(), newNombre.get()), labelID.destroy(), idDisco.destroy(), preguntaNombre.destroy(), newNombre.destroy(), botonEnviar.destroy()])
    botonEnviar.pack()
    
def modAnho(fichero,arbol,ventana, idDisco, newNombre):
    idcorrecto = False
    for disco in arbol.findall('disco'):
        if disco.get('id') == idDisco:
            idcorrecto = True
            disco.set('anho', newNombre)
    if(idcorrecto):
        messagebox.showinfo("Mod Nombre", "Nombre modificado correctamente")
    else:
        messagebox.showerror("Error", "El id introducido no corresponde con ningun disco")
    arbol.write(fichero)
    ventana.destroy()

def pregAnho(fichero , arbol, ventana):
    labelID = Label(ventana, text="Cual es el id del disco que quieres modificar")
    labelID.pack()
    idDisco = Entry(ventana, width=50)
    idDisco.pack()
    preguntaNombre = Label(ventana, text="Cual es el nuevo nombre de artista del disco")
    preguntaNombre.pack()
    newNombre = Entry(ventana, width=50)
    newNombre.pack()
    botonEnviar = Button(ventana, text= "Modificar", command=lambda:[modAnho(fichero,arbol,ventana, idDisco.get(), newNombre.get()), labelID.destroy(), idDisco.destroy(), preguntaNombre.destroy(), newNombre.destroy(), botonEnviar.destroy()])
    botonEnviar.pack()

def llamadaBotonMod():
    wndMod = Toplevel()
    wndMod.geometry("500x500")
    fichero = askopenfilename()
    ficheroXml = open(fichero)
    arbol = ElementTree()
    arbol.parse(fichero)

    nombre = Button(wndMod, text="Modificar el nombre de un disco", command=lambda:[pregNombre(fichero , arbol, wndMod), nombre.destroy(), artista.destroy(), genero.destroy(),anho.destroy()])
    nombre.pack()
    artista = Button(wndMod, text="Modificar el artista de un disco", command=lambda:[pregArtista(fichero , arbol, wndMod), nombre.destroy(), artista.destroy(), genero.destroy(),anho.destroy()])
    artista.pack()
    genero = Button(wndMod, text="Modificar el genero de un disco", command=lambda:[pregGenero(fichero , arbol, wndMod), nombre.destroy(), artista.destroy(), genero.destroy(),anho.destroy()])
    genero.pack()
    anho = Button(wndMod,text="Modificar el año de un disco", command=lambda:[pregAnho(fichero , arbol, wndMod), nombre.destroy(), artista.destroy(), genero.destroy(),anho.destroy()])
    anho.pack()

    

    
def mostrar():
    filename = askopenfilename()
    
    tree = ET.parse(filename)
    root = tree.getroot()
    contador = 1
    contadorCanciones = 1
    global texto
    texto = ""
    for disco in root:
        #if disco.attrib['id'] == str(contador):
        texto = texto + "Disco: " + disco.attrib['nombre'] + "\n"
        texto = texto + "Artista: " + disco.attrib['artista'] + "\n"
        texto = texto + "Genero: " + disco.attrib['genero'] + "\n"
        texto = texto + "Año: " + disco.attrib['anho'] + "\n"
        texto = texto + ""
        texto = texto + "Canciones" + "\n"   
        for cancion in disco:
            texto = texto + "Cancion "+ str(contadorCanciones) + ":" + "\n"
            texto = texto + " Nombre: " + cancion.attrib['Cnombre'] + "\n"
            texto = texto + " Duracion: " + cancion.attrib['duracion'] + "\n"
            contadorCanciones += 1
        texto = texto + "\n"
        contadorCanciones = 1        
        print("\n")
        contador += 1

    wndMostrar = Toplevel()
    
    wndMostrar.geometry("250x500")
    wndMostrar.title("Discos y canciones")
    w = Scrollbar(wndMostrar)
    prueba = Text(wndMostrar, height=10 , width=30, )

    w.pack(side = RIGHT, fill = Y)
    prueba.pack(side= "left", fill= Y , padx= 10)
    w.config(command= prueba.yview)
    prueba.config(yscrollcommand=w.set)
    prueba.insert(END, texto )

def setearCanciones(numCanc, wndCancion, arrayDiscos, fileName, copia ):
    disco = []
    main_frame = Frame(wndCancion)
    main_frame.pack(fill="both", expand=1)
    

    my_canvas = Canvas(main_frame)
    my_canvas.pack(side=LEFT, fill= BOTH, expand=1)

    my_scrollbar = ttk.Scrollbar(main_frame, orient=VERTICAL, command=my_canvas.yview)
    my_scrollbar.pack(side=RIGHT, fill=Y)

    my_canvas.configure(yscrollcommand=my_scrollbar.set)
    my_canvas.bind('<Configure>', lambda e: my_canvas.configure(scrollregion = my_canvas.bbox("all")))

    second_frame = Frame(my_canvas)
    my_canvas.create_window((0,0), window= second_frame, anchor="nw")

    for i in range(int(numCanc)):
        nombre = Label(second_frame, text= "Nombre de la cancion" + str(i+1))
        nombre.pack(padx=20)
        inputNombre = Entry(second_frame, width=50)
        inputNombre.pack(padx=100)
        duracion = Label(second_frame, text= "Duracion de la cancion")
        duracion.pack()
        inputDuracion = Entry(second_frame, width=50)
        inputDuracion.pack()
        cancionActual = []
        cancionActual.append(inputNombre)
        cancionActual.append(inputDuracion)
        disco.append(cancionActual)

    cancion.append(disco)
    confirmarCanciones = Button(wndCancion, text="Enviar", command=lambda:[addCanciones(arrayDiscos, fileName, copia)])
    confirmarCanciones.pack()
    
def addCanciones(arrayDiscos, fileName, copia):
    if(len(copia) > 0):
        disco = arrayDiscos[len(arrayDiscos) - len(copia)]
        copia.pop()
        wndCancion = Toplevel()
        wndCancion.geometry("500x500")                 
        pregunta = Label(wndCancion, text="Cuantas canciones tiene el disco: " + disco[0].get())
        pregunta.pack()
        Ncanciones = Entry(wndCancion, width=20,)
        Ncanciones.pack()
        mostrarCampoCanciones = Button(wndCancion, text="Confirmar", command=lambda: setearCanciones(Ncanciones.get(), wndCancion, arrayDiscos, fileName, copia))
        mostrarCampoCanciones.pack()
    else:
        
        addDiscos(arrayDiscos, fileName)
        messagebox.showinfo("INFORMACION", "Creado correctamente")

def addDiscos(arrayDiscos, fileName):
    
    discos = ET.Element("discos")
    i = 1
    for disco in arrayDiscos:
        h1 =ET.SubElement(discos, "disco", id= str(i), nombre = disco.pop(0).get(), artista = disco.pop(0).get(), genero =disco.pop(0).get(), anho =disco.pop(0).get())
        
        for j in cancion[i-1]:
            ET.SubElement(h1, "cancion", Cnombre = j[0].get(), duracion = j[1].get())
        i+=1    
    archivo = ET.ElementTree(discos)

    archivo.write(fileName + ".xml", encoding= "utf-8")
 
def parseoYEntrys(cadena , ventana):
    wndCrear = ventana
    nDiscos = int(cadena)
    
    i = 1
    
    main_frame = Frame(wndCrear)
    main_frame.pack(fill="both", expand=1)
    

    my_canvas = Canvas(main_frame)
    my_canvas.pack(side=LEFT, fill= BOTH, expand=1)

    my_scrollbar = ttk.Scrollbar(main_frame, orient=VERTICAL, command=my_canvas.yview)
    my_scrollbar.pack(side=RIGHT, fill=Y)

    my_canvas.configure(yscrollcommand=my_scrollbar.set)
    my_canvas.bind('<Configure>', lambda e: my_canvas.configure(scrollregion = my_canvas.bbox("all")))

    second_frame = Frame(my_canvas)
    my_canvas.create_window((0,0), window= second_frame, anchor="nw")
    
    arrayDiscos = []
    
    Label(second_frame, text="Como quieres que se llame el archivo xml").pack()
    fileName = Entry(second_frame,width=50)
    fileName.pack()

    

    for i in range(nDiscos):
        disco = []
        nombre = Label(second_frame, text= "Nombre del disco " + str(i+1))
        nombre.pack(padx=20)
        inputNombre = Entry(second_frame, width=50)
        inputNombre.pack(padx=100)
        artista = Label(second_frame, text= "Nombre del artista")
        artista.pack()
        inputArtista = Entry(second_frame, width=50)
        inputArtista.pack()
        genero = Label(second_frame, text= "Genero del disco")
        genero.pack()
        inputGenero = Entry(second_frame ,width=50)
        inputGenero.pack()
        anho = Label(second_frame, text="Año del disco")
        anho.pack()
        inputAnho = Entry(second_frame, width=50)
        inputAnho.pack()
        i+=1

        disco.append(inputNombre)
        disco.append(inputArtista)
        disco.append(inputGenero)
        disco.append(inputAnho)
        arrayDiscos.append(disco)
        
    copia = arrayDiscos.copy()
    
    addDisco = Button(second_frame, text="Añadir discos", command=lambda:addCanciones(arrayDiscos, fileName.get(),copia))
    addDisco.pack()
   
def createAudioteca():
    wndCrear = Toplevel()
    wndCrear.geometry("500x500")
    
   
    pregunta = Label(wndCrear, text="Cuantos discos tiene tu audioteca")
    pregunta.pack()
    entryPregunta = Entry(wndCrear, width=50)
    entryPregunta.pack()
    
    botonEnviar = Button(wndCrear, text="Enviar")
    botonEnviar.config(command=lambda:[parseoYEntrys(entryPregunta.get(), wndCrear),botonEnviar.destroy(), pregunta.destroy(), entryPregunta.destroy()])
    botonEnviar.pack()

imgShow = PhotoImage(file='Mostrar.png')
botonMostrar=Button(app, image=imgShow, command=mostrar).place(x=150, y=50)

imgCreate = PhotoImage(file='Create.png')
botonCrear=Button(app, image=imgCreate, command= createAudioteca).place(x=150, y=130)

imgMod = PhotoImage(file='Mod.png')
botonMod=Button(app, image=imgMod, command=llamadaBotonMod).place(x=150, y=210)


app.mainloop()