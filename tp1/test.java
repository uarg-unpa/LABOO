public void actualizarImparesMenores53() throws IOException {
        archivo.seek(0);
                while (archivo.getFilePointer() < archivo.length()) {
            
            long posicionOriginal = archivo.getFilePointer(); 
            
            int value = archivo.readInt();
            
            if (value % 2 != 0 && value < 53) {
    
                archivo.seek(posicionOriginal);
                archivo.writeInt(value + 2);
            }
        }
    }