package br.com.senai.core.service;

import java.util.List;

import br.com.senai.core.dao.DaoOrdemServico;
import br.com.senai.core.dao.FactoryDao;
import br.com.senai.core.domain.Servico;

public class OrdemServicoService {
	private DaoOrdemServico dao;
	
	public OrdemServicoService() {
		this.dao = FactoryDao.getInstance().getDaoPostgresqlOrdemServico();
	}
	
	public void salvar(Servico ordem) {
		this.validar(ordem);
		if(ordem.isJaInserido()) {
			this.dao.alterar(ordem);
		} else {
			this.dao.salvar(ordem);
		}
	}
	
	public void validar(Servico ordem) {
		if(ordem != null) {
			boolean isNomeServicoInvalido = ordem.getNomeServico().length() < 0;
			if(isNomeServicoInvalido) {
				throw new IllegalArgumentException("O nome do serviço não pode ser nulo");
			}
			
			boolean isNomePlacaInvalido = ordem.getNomePlaca().length() < 0;
			if(isNomePlacaInvalido) {
				throw new IllegalArgumentException("A placa não pode ser nula!");
			}
			
			boolean isClienteInvalido = ordem.getCliente() == null;
			if(isClienteInvalido) {
				throw new IllegalArgumentException("O cliente não pode ser nulo!");
			}
			
			boolean isAtendenteInvalido = ordem.getAtendente() == null;
			if(isAtendenteInvalido) {
				throw new IllegalArgumentException("O Atendente não pode ser nulo!");
			}
			
			boolean isMecanicoInvalido = ordem.getMecanico() == null;
			if(isMecanicoInvalido) {
				throw new IllegalArgumentException("O mecânico não pode ser nulo!");
			}
		} else {
			throw new IllegalArgumentException("A ordem de serviço não pode ser nula!");
		}
	}
	
	public List<Servico> listarTodas() {
		return this.dao.listarTodas();
	}
	
	public void excluirPor(int idDaOrdem) {
		if(idDaOrdem > 0) {
			this.dao.excluirPor(idDaOrdem);
		}else {
			throw new IllegalArgumentException("O id da ordem não "
					+ "pode ser menor do que 1");
		}

    }
	

}
