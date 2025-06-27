import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

// Classe principal do jogo, estende JPanel para desenhar e implementa ActionListener para o loop do jogo e KeyListener para input do teclado
public class FlappyBirdGame extends JPanel implements ActionListener, KeyListener {

    // Constantes do jogo
    private static final int LARGURA_JANELA = 800;
    private static final int ALTURA_JANELA = 600;
    private static final int LARGURA_PASSARO = 40;
    private static final int ALTURA_PASSARO = 30;
    private static final int LARGURA_CANO = 70;
    private static final int ESPACO_ENTRE_CANOS = 180; // Espaço vertical entre os canos
    private static final int DISTANCIA_CANOS = 300; // Distância horizontal entre os pares de canos
    private static final int VELOCIDADE_JOGO = 15; // Velocidade de atualização do jogo (ms)
    private static final double GRAVIDADE = 0.6;
    private static final double FORCA_PULO = -8;

    // Variáveis do jogo
    private Passaro passaro;
    private ArrayList<Cano> canos;
    private Timer temporizador;
    private int pontuacao;
    private boolean jogoIniciado;
    private boolean fimDeJogo;
    private Random random;

    // Construtor
    public FlappyBirdGame() {
        setPreferredSize(new Dimension(LARGURA_JANELA, ALTURA_JANELA));
        setBackground(new Color(135, 206, 235)); // Cor de fundo azul claro
        setFocusable(true); // Permite que o painel receba eventos de teclado
        addKeyListener(this); // Adiciona o listener de teclado

        configurarJogo();
    }

    // Configura o estado inicial do jogo
    private void configurarJogo() {
        passaro = new Passaro();
        canos = new ArrayList<>();
        pontuacao = 0;
        jogoIniciado = false;
        fimDeJogo = false;
        random = new Random();

        // Inicializa o temporizador
        if (temporizador != null) {
            temporizador.stop();
        }
        temporizador = new Timer(VELOCIDADE_JOGO, this);
    }

    // Método para iniciar o jogo
    public void iniciarJogo() {
        jogoIniciado = true;
        temporizador.start();
    }

    // Loop principal do jogo, chamado pelo temporizador
    @Override
    public void actionPerformed(ActionEvent e) {
        if (jogoIniciado && !fimDeJogo) {
            atualizarJogo();
            repaint(); // Redesenha a tela
        }
    }

    // Atualiza o estado do jogo
    private void atualizarJogo() {
        // Atualiza a posição do pássaro
        passaro.atualizar();

        // Move os canos e gera novos
        for (int i = 0; i < canos.size(); i++) {
            Cano cano = canos.get(i);
            cano.mover();

            // Remove canos que saíram da tela
            if (cano.getX() + LARGURA_CANO < 0) {
                canos.remove(i);
                i--;
            }

            // Verifica colisão do pássaro com este cano
            if (cano.colisao(passaro)) {
                fimDeJogo = true;
            }

            // Aumenta a pontuação se o pássaro passou pelo cano
            if (!cano.isPassado() && passaro.getX() > cano.getX() + LARGURA_CANO) {
                pontuacao++;
                cano.setPassado(true);
            }
        }

        // Gera novos canos
        if (canos.isEmpty() || canos.get(canos.size() - 1).getX() < LARGURA_JANELA - DISTANCIA_CANOS) {
            gerarNovoCano();
        }

        // Verifica colisão com o chão ou teto
        if (passaro.getY() + ALTURA_PASSARO > ALTURA_JANELA || passaro.getY() < 0) {
            fimDeJogo = true;
        }

        // Se o jogo terminou, para o temporizador
        if (fimDeJogo) {
            temporizador.stop();
        }
    }

    // Gera um novo par de canos (superior e inferior)
    private void gerarNovoCano() {
        int alturaAleatoria = random.nextInt(ALTURA_JANELA - ESPACO_ENTRE_CANOS - 100) + 50; // Altura do cano superior
        canos.add(new Cano(LARGURA_JANELA, alturaAleatoria));
    }

    // Desenha todos os elementos do jogo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Desenha o fundo

        passaro.desenhar(g); // Desenha o pássaro

        // Desenha todos os canos
        for (Cano cano : canos) {
            cano.desenhar(g);
        }

        // Desenha a pontuação
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Pontuação: " + pontuacao, 10, 30);

        // Mensagens de início/fim de jogo
        if (!jogoIniciado) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            String mensagemInicio = "Pressione ESPAÇO para começar!";
            int larguraTexto = g.getFontMetrics().stringWidth(mensagemInicio);
            g.drawString(mensagemInicio, (LARGURA_JANELA - larguraTexto) / 2, ALTURA_JANELA / 2 - 20);
        }

        if (fimDeJogo) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            String mensagemFim = "FIM DE JOGO!";
            String mensagemReiniciar = "Pressione R para Reiniciar";
            int larguraFim = g.getFontMetrics().stringWidth(mensagemFim);
            int larguraReiniciar = g.getFontMetrics().stringWidth(mensagemReiniciar);
            g.drawString(mensagemFim, (LARGURA_JANELA - larguraFim) / 2, ALTURA_JANELA / 2 - 50);
            g.drawString(mensagemReiniciar, (LARGURA_JANELA - larguraReiniciar) / 2, ALTURA_JANELA / 2 + 20);
        }
    }

    // Métodos KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
        // Não utilizado
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!jogoIniciado) {
                iniciarJogo();
            }
            if (!fimDeJogo) {
                passaro.saltar();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_R && fimDeJogo) {
            configurarJogo(); // Reinicia o jogo
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Não utilizado
    }

    // Classe interna para o Pássaro
    private class Passaro {
        private int x, y;
        private double velocidadeY;

        public Passaro() {
            x = LARGURA_JANELA / 4;
            y = ALTURA_JANELA / 2 - ALTURA_PASSARO / 2;
            velocidadeY = 0;
        }

        public void atualizar() {
            velocidadeY += GRAVIDADE; // Aplica gravidade
            y += velocidadeY; // Atualiza posição vertical
        }

        public void saltar() {
            velocidadeY = FORCA_PULO; // Aplica força de pulo
        }

        public void desenhar(Graphics g) {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, LARGURA_PASSARO, ALTURA_PASSARO); // Desenha o corpo do pássaro
            // Olho
            g.setColor(Color.BLACK);
            g.fillOval(x + LARGURA_PASSARO * 3 / 4, y + ALTURA_PASSARO / 4, 5, 5);
            // Bico
            g.setColor(Color.ORANGE);
            int[] bicoX = {x + LARGURA_PASSARO, x + LARGURA_PASSARO + 10, x + LARGURA_PASSARO};
            int[] bicoY = {y + ALTURA_PASSARO / 4, y + ALTURA_PASSARO / 2, y + ALTURA_PASSARO * 3 / 4};
            g.fillPolygon(bicoX, bicoY, 3);
        }

        // Getters para a posição e dimensões do pássaro
        public int getX() { return x; }
        public int getY() { return y; }
        public int getLargura() { return LARGURA_PASSARO; }
        public int getAltura() { return ALTURA_PASSARO; }
    }

    // Classe interna para o Cano
    private class Cano {
        private int x;
        private int alturaTopo;
        private boolean passado; // Indica se o pássaro já passou por este cano

        public Cano(int x, int alturaTopo) {
            this.x = x;
            this.alturaTopo = alturaTopo;
            this.passado = false;
        }

        public void mover() {
            x -= 3; // Move o cano para a esquerda
        }

        public void desenhar(Graphics g) {
            g.setColor(Color.GREEN.darker()); // Cor do cano
            // Cano superior
            g.fillRect(x, 0, LARGURA_CANO, alturaTopo);
            // Cano inferior (calcula a posição com base no espaço e na altura do cano superior)
            g.fillRect(x, alturaTopo + ESPACO_ENTRE_CANOS, LARGURA_CANO, ALTURA_JANELA - (alturaTopo + ESPACO_ENTRE_CANOS));
        }

        // Verifica colisão com o pássaro
        public boolean colisao(Passaro p) {
            // Colisão com o cano superior
            Rectangle rectPassaro = new Rectangle(p.getX(), p.getY(), p.getLargura(), p.getAltura());
            Rectangle rectCanoTopo = new Rectangle(x, 0, LARGURA_CANO, alturaTopo);
            Rectangle rectCanoBase = new Rectangle(x, alturaTopo + ESPACO_ENTRE_CANOS, LARGURA_CANO, ALTURA_JANELA - (alturaTopo + ESPACO_ENTRE_CANOS));

            return rectPassaro.intersects(rectCanoTopo) || rectPassaro.intersects(rectCanoBase);
        }

        // Getters e setters
        public int getX() { return x; }
        public boolean isPassado() { return passado; }
        public void setPassado(boolean passado) { this.passado = passado; }
    }

    // Método main para criar a janela do jogo
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird em Java");
        FlappyBirdGame jogo = new FlappyBirdGame();
        frame.add(jogo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack(); // Ajusta o tamanho da janela ao conteúdo
        frame.setLocationRelativeTo(null); // Centraliza a janela
        frame.setVisible(true);
    }
}
