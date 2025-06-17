use std::io;
use std::fs;
use std::path::Path;

fn main(){
    println!("Digite o caminho do arquivo: ")

    let mut caminho = String::new();

    io::stdin().readline(&mut caminho).expect("Falha ao ler a entrada");
    
    let caminho= caminho.trim();

    if !Path::new(caminho).exists(){
        println!("Caminho nao encontrado")
        return;
    }

    match fs::read_to_string(caminho){
        Ok(conteudo)=>{ println!("");}
        Err(erro)=>{println!(r#"Erro ao ler o arquivo"#,erro);}
    }
}